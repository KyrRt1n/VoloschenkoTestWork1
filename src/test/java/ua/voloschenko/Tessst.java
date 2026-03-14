package ua.voloschenko;

import org.junit.jupiter.api.Test;
import ua.voloschenko.exceptions.OutOfStockException;
import ua.voloschenko.exceptions.ValidationException;
import ua.voloschenko.model.*;
import ua.voloschenko.payment.CardPayment;
import ua.voloschenko.processors.DefaultOrderProcessor;
import ua.voloschenko.processors.OrderProcessorTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class Tessst {

    OrderProcessorTemplate processor = new DefaultOrderProcessor(new CardPayment());

    @Test
    void successfulOrder() {
        OrderItem[] items = {
                new OrderItem("Pr", "Laptop", 1, new Money(5000, "UAH"))
        };
        Order order = new Order(new Email("avol@gmail.com"), items);
        processor.process(order);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void outOfStockThrowsException() {
        OrderItem[] items = {
                new OrderItem("Outfostok", "NoStock", 1, new Money(500, "UAH"))
        };
        Order order = new Order(new Email("avol@gmail.com"), items);
        assertThrows(OutOfStockException.class, () -> processor.process(order));
    }

    @Test
    void tooManyItemsThrowsException() {
        OrderItem[] items = new OrderItem[11];
        for (int i = 0; i < 11; i++) {
            items[i] = new OrderItem("P" + i, "Product" + i, 1, new Money(100, "UAH"));
        }
        Order order = new Order(new Email("avol@gmail.com"), items);
        assertThrows(ValidationException.class, () -> processor.process(order));
    }
}

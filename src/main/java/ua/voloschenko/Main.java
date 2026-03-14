package ua.voloschenko;

import ua.voloschenko.model.*;
import ua.voloschenko.payment.*;
import ua.voloschenko.processors.*;
import ua.voloschenko.service.OrderService;

public class Main {
    public static void main(String[] args) {

        OrderItem[] items = {
                new OrderItem("P1", "Laptop", 1, new Money(15000, "UAH")),
                new OrderItem("P2", "Mouse", 2, new Money(500, "UAH"))
        };

        Order order = new Order(new Email("avol@gmail.com"), items);

        OrderService service = new OrderService();
        service.save(order);

        OrderProcessorTemplate processor = new DefaultOrderProcessor(new CardPayment());
        processor.process(order);

        service.findById(order.getId())
                .ifPresentOrElse(
                        o -> System.out.println("Found: " + o),
                        () -> System.out.println("Order not found")
                );
    }
}
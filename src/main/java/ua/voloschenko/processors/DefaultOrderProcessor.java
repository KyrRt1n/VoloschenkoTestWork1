package ua.voloschenko.processors;

import ua.voloschenko.exceptions.*;
import ua.voloschenko.model.*;
import ua.voloschenko.payment.PaymentMethod;
import ua.voloschenko.processors.OrderProcessorTemplate;

public class DefaultOrderProcessor extends OrderProcessorTemplate {

    private static final int MAX_ITEMS = 10;

    private final PaymentMethod paymentMethod;

    public DefaultOrderProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    protected void validate(Order order) {
        if (order.getItems().length > MAX_ITEMS)
            throw new ValidationException("Order has more than 10 items");
        if (order.getStatus() != OrderStatus.NEW)
            throw new ValidationException("Order must be in NEW status");
        System.out.println("INFO: Validation passed for order: {" + order.getId() + "}");
    }

    @Override
    protected void reserveStock(Order order) {
        for (var item : order.getItems()) {
            if (item.getProductId().startsWith("OUT_")) {
                System.out.println("WARN: Product out of stock: {" + item.getProductId() + "}");
                throw new OutOfStockException(item.getProductId());
            }
        }
        System.out.println("INFO: Stock reserved for order: {" + order.getId() + "}");
    }

    @Override
    protected void pay(Order order) {
        paymentMethod.pay(order.getTotalAmount());
        System.out.println("INFO: Payment done via {" + paymentMethod.getName() + "} for order: {" + order.getId() + "}");
    }
}
package ua.voloschenko.processors;

import ua.voloschenko.model.Order;
import ua.voloschenko.model.OrderStatus;
import ua.voloschenko.model.Money;

public abstract class OrderProcessorTemplate {

    public final void process(Order order) {
        System.out.println("INFO: Starting order processing: {" + order.getId() + "}");
        validate(order);
        calculateTotal(order);
        reserveStock(order);
        pay(order);
        complete(order);
        System.out.println("INFO: Order processed successfully: {" + order.getId() + "}");
    }

    protected abstract void validate(Order order);
    protected abstract void reserveStock(Order order);
    protected abstract void pay(Order order);

    protected void calculateTotal(Order order) {
        double total = 0;
        for (var item : order.getItems()) {
            total += item.totalPrice().getAmount();
        }
        Money money = new Money(total, "UAH");

        if (total >= 10_000) {
            money = money.discount(5);
            System.out.println("INFO: Discount 5%, new total: {" + money + "}");
        }

        order.setTotalAmount(money);
        System.out.println("INFO: Total calculated: {" + money + "}");
    }

    protected void complete(Order order) {
        order.transitionTo(OrderStatus.PAID);
        System.out.println("INFO: Order completed: {" + order.getId() + "}");
    }
}
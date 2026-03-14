package ua.voloschenko.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Order {

    private final String id;
    private final Email customerEmail;
    private final OrderItem[] items;
    private OrderStatus status;
    private Money totalAmount;

    public Order(Email customerEmail, OrderItem[] items) {
        this(UUID.randomUUID().toString(), customerEmail, items, OrderStatus.NEW);
    }

    public Order(String id, Email customerEmail, OrderItem[] items, OrderStatus status) {
        this.id = id;
        this.customerEmail = customerEmail;
        this.items = Arrays.copyOf(items, items.length);
        this.status = OrderStatus.NEW;
    }

    public void transitionTo(OrderStatus next) {
        if (!status.canTransitionTo(next))
            throw new IllegalStateException(status + " cant transition to " + next);
        this.status = next;
    }

    public void setTotalAmount(Money totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderItem[] getItems() {
        return Arrays.copyOf(items, items.length); // defensive copy при поверненні
    }

    public String getId() {
        return id;
    }

    public Email getCustomerEmail() {
        return customerEmail;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", status=" + status + ", total=" + totalAmount + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

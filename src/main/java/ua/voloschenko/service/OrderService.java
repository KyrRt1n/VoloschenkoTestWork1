package ua.voloschenko.service;

import ua.voloschenko.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {

    private final List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
        System.out.println("INFO: Order saved: {" + order.getId() + "}");
    }

    public Optional<Order> findById(String id) {
        return orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    public List<Order> listAll() {
        return new ArrayList<>(orders);
    }
}
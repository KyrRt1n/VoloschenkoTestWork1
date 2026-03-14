package ua.voloschenko.payment;

import ua.voloschenko.model.Money;

public interface PaymentMethod {
    void pay(Money amount);
    String getName();
}

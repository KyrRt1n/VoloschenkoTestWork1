package ua.voloschenko.payment;

import ua.voloschenko.exceptions.*;
import ua.voloschenko.model.Money;

public class CardPayment implements PaymentMethod {

    private static final double MAX_AMOUNT = 30_000;

    @Override
    public void pay(Money amount) {
        if (amount.getAmount() > MAX_AMOUNT)
            throw new PaymentException("Card limit exceeded: " + amount);
    }

    @Override
    public String getName() { return "CardPayment"; }
}

package ua.voloschenko.payment;

import ua.voloschenko.exceptions.*;
import ua.voloschenko.model.Money;

public class PayPalPayment implements PaymentMethod {

    private static final double MIN_AMOUNT = 300;

    @Override
    public void pay(Money amount) {
        if (amount.getAmount() < MIN_AMOUNT)
            throw new PaymentException("PayPal minimum is 300: " + amount);
    }

    @Override
    public String getName() { return "PayPalPayment"; }
}

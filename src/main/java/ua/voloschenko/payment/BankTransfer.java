package ua.voloschenko.payment;

import ua.voloschenko.exceptions.*;
import ua.voloschenko.model.Money;

public class BankTransfer implements PaymentMethod{

    private static final double COMMISSION = 0.02;

    @Override
    public void pay(Money amount) {
        double total = amount.getAmount() * (1 + COMMISSION);
        System.out.println("BankTransfer paid: " + total + " (with 2% commission)");
    }

    @Override
    public String getName() {
        return "BankTransferPayment";
    }

}

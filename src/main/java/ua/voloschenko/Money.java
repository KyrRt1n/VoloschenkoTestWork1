package ua.voloschenko;

import java.util.Objects;

public class Money {

    private double amount;
    private String currency;

    public Money(double amount, String currency) {
        if((amount < 0) )
            throw new IllegalArgumentException("Amount must be positive");

        if(currency == null || currency.isEmpty())
            throw new IllegalArgumentException("Currency must be non-empty");

        this.amount = amount;
        this.currency = currency;
    }

    public Money discount(double percent) {
        if(percent < 0)
            throw new IllegalArgumentException("Discount percent must be positive");
        double discount = 1 - percent / 100;
        return new Money(discount*amount, this.currency);
    }

    public Money multiply(double mult) {
        return new Money(mult*amount, this.currency);
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Money m)) return false;
        return Double.compare(amount, m.amount) == 0 && currency.equals(m.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

}

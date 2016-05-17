package com.trimplement;

import java.math.BigDecimal;


/**
 * Attention! Class just for candidates logical test
 */
public class Money {

    private final double amount;
    private final String currency;

    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Money add(Money what) {
        BigDecimal bdAmount = BigDecimal.valueOf(amount);
        BigDecimal whatAmount = BigDecimal.valueOf(what.getAmount());
        BigDecimal sum = bdAmount.add(whatAmount);
        return new Money(sum.doubleValue(), currency);
    }

    public Money subtract(Money what) {
        BigDecimal bdAmount = BigDecimal.valueOf(amount);
        BigDecimal whatAmount = BigDecimal.valueOf(what.getAmount());
        BigDecimal diff = bdAmount.subtract(whatAmount);

        double newAmount = diff.doubleValue();
        return new Money(newAmount, currency);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Money other = (Money) obj;
        if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
            return false;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        return true;
    }

}

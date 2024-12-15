package com.example.demo.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SavingsAccount extends Account {

    private final IntegerProperty withdrawalLimit;

    public SavingsAccount(String owner, String accountNumber, double balance, int wLimit) {
        super(owner, accountNumber, balance);
        this.withdrawalLimit = new SimpleIntegerProperty(this,"Withdrawal Limit", wLimit);
    }

    public IntegerProperty withdrawalLimitProp() {
        return withdrawalLimit;
    }

    @Override
    public String toString() {
        return accountNumberProperty().get();
    }
}

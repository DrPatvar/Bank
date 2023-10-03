package com.example.bank.model;

public enum Operation {
    DEPOSIT(),
    WITHDRAW,
    TRANSFER;

    @Override
    public String toString() {
        return name();
    }
}

package com.trimplement.enums;

public enum PaymentInstrumentStatus {

    Active("Payment instrument %s  is activated", "Payment instrument %s is already active"),
    Locked("Payment instrument %s  locked", "Payment instrument %s is already locked");

    private final String successfulMessage;
    private final String failedMessage;

    PaymentInstrumentStatus(String successfulMessage, String failedMessage) {
        this.successfulMessage = successfulMessage;
        this.failedMessage = failedMessage;
    }

    public String getSuccessfulMessage() {
        return successfulMessage;
    }

    public String getFailedMessage() {
        return failedMessage;
    }
}

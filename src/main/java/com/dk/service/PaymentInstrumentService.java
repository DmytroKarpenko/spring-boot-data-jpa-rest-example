package com.dk.service;

import com.dk.data.model.PaymentInstrument;
import com.dk.enums.CreditCardScheme;
import com.dk.enums.PaymentInstrumentStatus;

import java.util.List;

public interface PaymentInstrumentService {

    List<PaymentInstrument> findPaymentInstruments();

    String updateLockStatusPaymentInstrument(
            long paymentInstrumentId, PaymentInstrumentStatus paymentInstrumentStatus
    );

    long createBankAccount(
            String accountHolder,
            String bankName,
            String iban,
            String bic
    );

    long createCreditCard(
            String accountHolder,
            CreditCardScheme scheme,
            String pan,
            int expiryMonth,
            int expiryYear
    );

}

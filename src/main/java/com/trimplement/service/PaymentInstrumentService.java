package com.trimplement.service;

import com.trimplement.data.model.PaymentInstrument;
import com.trimplement.enums.CreditCardScheme;
import com.trimplement.enums.PaymentInstrumentStatus;

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

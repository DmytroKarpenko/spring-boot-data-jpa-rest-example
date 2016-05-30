package com.dk.service.impl;

import com.dk.data.model.AuditMessage;
import com.dk.data.model.BankAccount;
import com.dk.data.model.CreditCard;
import com.dk.data.model.PaymentInstrument;
import com.dk.data.repository.AuditMessageRepository;
import com.dk.data.repository.PaymentInstrumentRepository;
import com.dk.enums.CreditCardScheme;
import com.dk.enums.PaymentInstrumentStatus;
import com.dk.service.PaymentInstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentInstrumentServiceImpl implements PaymentInstrumentService {

    @Autowired
    private PaymentInstrumentRepository paymentInstrumentRepository;

    @Autowired
    private AuditMessageRepository auditMessageRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void createAuditMessage(String message) {
        AuditMessage auditMessage = new AuditMessage();
        auditMessage.setDate(new Date());
        auditMessage.setMessage(message);
        auditMessage.setUserName("Dummy");
        auditMessageRepository.save(auditMessage);
    }

    @Override
    @Transactional
    public List<PaymentInstrument> findPaymentInstruments() {
        Iterable<PaymentInstrument> paymentInstruments = paymentInstrumentRepository.findAll();
        List<PaymentInstrument> result = new ArrayList<PaymentInstrument>();
        for (PaymentInstrument paymentInstrument : paymentInstruments) {
            result.add(paymentInstrument);
        }
        return result;
    }

    @Override
    public String updateLockStatusPaymentInstrument(long paymentInstrumentId, PaymentInstrumentStatus paymentInstrumentStatus) {
        createAuditMessage(String.format("Locks payment instrument %s", paymentInstrumentId));
        PaymentInstrument paymentInstrument = paymentInstrumentRepository.findOne(paymentInstrumentId);
        if (paymentInstrument.getStatus() == paymentInstrumentStatus) {
            return String.format(paymentInstrumentStatus.getFailedMessage(), paymentInstrumentId);
        }
        paymentInstrument.setStatus(paymentInstrumentStatus);
        paymentInstrumentRepository.save(paymentInstrument);
        return String.format(paymentInstrumentStatus.getSuccessfulMessage(), paymentInstrumentId);
    }

    @Override
    public long createBankAccount(
            String accountHolder,
            String bankName,
            String iban,
            String bic
    ) {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setStatus(PaymentInstrumentStatus.Active);
        bankAccount.setCreationDateTime(new Date());
        bankAccount.setAccountHolder(accountHolder);
        bankAccount.setBankName(bankName);
        bankAccount.setIban(iban);
        bankAccount.setBic(bic);
        PaymentInstrument saved = paymentInstrumentRepository.save(bankAccount);
        return saved.getId();
    }

    @Override
    public long createCreditCard(
            String accountHolder,
            CreditCardScheme scheme,
            String pan,
            int expiryMonth,
            int expiryYear
    ) {

        CreditCard creditCard = new CreditCard();
        creditCard.setStatus(PaymentInstrumentStatus.Active);
        creditCard.setCreationDateTime(new Date());
        creditCard.setAccountHolder(accountHolder);
        creditCard.setScheme(scheme);
        creditCard.setPan(pan);
        creditCard.setExpiryMonth(expiryMonth);
        creditCard.setExpiryYear(expiryYear);
        PaymentInstrument saved = paymentInstrumentRepository.save(creditCard);
        return saved.getId();
    }

}

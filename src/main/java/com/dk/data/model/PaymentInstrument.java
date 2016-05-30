package com.dk.data.model;

import com.dk.enums.PaymentInstrumentStatus;
import com.dk.enums.PaymentInstrumentType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PaymentInstrument {

    private transient PaymentInstrumentType type;

    @Id
    @GeneratedValue
    private long id;

    @Column
    private PaymentInstrumentStatus status;

    @Column
    private Date creationDateTime;

    @Column
    private String accountHolder;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "paymentInstrument")
    private List<Transaction> transactions;

    public PaymentInstrument(PaymentInstrumentType type) {
        this.type = type;
    }

    public PaymentInstrumentType getType() {
        return type;
    }

    public void setType(PaymentInstrumentType type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PaymentInstrumentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentInstrumentStatus status) {
        this.status = status;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public abstract String getDescription();

}

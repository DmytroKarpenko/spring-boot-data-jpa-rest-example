package com.dk.data.repository;

import com.dk.data.model.PaymentInstrument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentInstrumentRepository extends CrudRepository<PaymentInstrument, Long> {
}

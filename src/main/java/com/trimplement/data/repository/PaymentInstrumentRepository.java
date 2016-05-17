package com.trimplement.data.repository;

import com.trimplement.data.model.PaymentInstrument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentInstrumentRepository extends CrudRepository<PaymentInstrument, Long> {
}

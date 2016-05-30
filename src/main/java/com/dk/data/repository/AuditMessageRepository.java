package com.dk.data.repository;

import com.dk.data.model.AuditMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditMessageRepository extends CrudRepository<AuditMessage, Long> {
}

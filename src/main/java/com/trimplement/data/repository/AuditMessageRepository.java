package com.trimplement.data.repository;

import com.trimplement.data.model.AuditMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditMessageRepository extends CrudRepository<AuditMessage, Long> {
}

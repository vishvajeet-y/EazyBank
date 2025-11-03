package com.vish.loan.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {//this return current auditor of the application
        return Optional.of("Loan_MS");
    }
}
/*
With help of SpringData JPA we can update metadata column automatically.

 */
package com.vish.account.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    /*This return current Auditor of the application , however we are returning hard coded value of now
    Later when we integrate microservice with Spring Data JPA Framework to enforce security
    we will be seeing how to populate logged in user
     */
    @Override
    public Optional<String>getCurrentAuditor(){
        return Optional.of("Account_MS");
    }
}

package com.kenschool.Auditing;

import lombok.val;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

// This auditing class is done inorder to select the current user name who logged in and to map it in the Entity field
@Component("AuditImplClass")
public class AuditingImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        val CurrentUser = SecurityContextHolder.getContext().getAuthentication().getName();
//    Authentication authentication= (Authentication) new SecurityContextHolder();
        return Optional.ofNullable(CurrentUser);
    }
}

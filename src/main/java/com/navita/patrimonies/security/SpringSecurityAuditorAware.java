package com.navita.patrimonies.security;

import com.navita.patrimonies.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {

	@Autowired
	private AuthenticatedUserRetrieval authenticatedUserRetrieval;

	@Override
	public Optional<User> getCurrentAuditor() {
		return Optional.ofNullable( authenticatedUserRetrieval.retrieve() );
	}
}

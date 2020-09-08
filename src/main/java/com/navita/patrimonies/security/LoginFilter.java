package com.navita.patrimonies.security;

import com.google.gson.Gson;
import com.navita.patrimonies.dtos.CredentialsDTO;
import com.navita.patrimonies.entities.User;
import com.navita.patrimonies.respositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	private final UserRepository userRepository;

	public LoginFilter(String url, AuthenticationManager authManager, UserRepository userRepository) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		this.userRepository = userRepository;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		CredentialsDTO credentials = new Gson().fromJson( request.getReader(), CredentialsDTO.class );

		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						credentials.getLogin(), credentials.getPassword(), Collections.emptyList()
				)
		);
	}

	@Override
	protected void successfulAuthentication(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain,
			Authentication auth) throws IOException, ServletException {
		//User is always present in successful authentication scenarios
		Optional<User> o = userRepository.findByLogin( auth.getName() );
		o.ifPresent( u ->  AuthenticationService.setHeaders(response, u));
	}

}

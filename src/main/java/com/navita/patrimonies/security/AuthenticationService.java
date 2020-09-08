package com.navita.patrimonies.security;

import com.navita.patrimonies.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

public class AuthenticationService {

    private static final long ONE_MONTH = 2_580_000_000L;
    private static final Date EXPIRATION = new Date(System.currentTimeMillis() + ONE_MONTH);
    private static final String SECRET = "9LkPb9b[vT?XQ=2kS2]v}B{KYV+wf<Wh>~ij{fHMbb58H6kD4[R((tNt4AP)w;h:}KS";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "X-Authorization";

    public static void setHeaders(HttpServletResponse response, User user) {
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + buildJWT(user));
    }

    private static String buildJWT(User user) {
        return Jwts.builder()
                .setSubject(user.getName())
                .claim("userName", user.getName())
                .claim("userLogin", user.getLogin())
                .setExpiration(EXPIRATION)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        return getHeader(request)
                .map(AuthenticationService::getUsedId)
                .map(uid -> new UsernamePasswordAuthenticationToken(uid, null, Collections.emptyList()))
                .orElse(null);
    }

    private static Optional<String> getHeader(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HEADER_STRING));
    }

    static String getUsedId(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
    }

}

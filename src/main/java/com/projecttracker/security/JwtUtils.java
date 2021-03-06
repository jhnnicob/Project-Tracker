package com.projecttracker.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

@Component
public class JwtUtils {
	@Value("${project_tracker.app.jwtSecret}")
	private String jwtSecret;

	@Value("${project_tracker.app.jwtExpirationInMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {

		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			//logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			//logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			//logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			//logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			//logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}

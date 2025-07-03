package com.huaxiexiyan.erp.infrastructure.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.huaxiexiyan.erp.infrastructure.config.JwtProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xiyan
 * @date 2025/7/1 18:10
 */
@Component
public class JwtUtil {

	private final JwtProperties properties;
	private final Algorithm algorithm;
	private final JWTVerifier verifier;

	public JwtUtil(JwtProperties properties) {
		this.properties = properties;
		this.algorithm = Algorithm.HMAC256(properties.getSecret());
		this.verifier = JWT.require(algorithm)
			.withIssuer(properties.getIssuer())
			.build();
	}

	public String generateToken(String subject) {
		Date now = new Date();
		Date expireAt = new Date(now.getTime() + properties.getExpireSeconds() * 1000);

		return JWT.create()
			.withSubject(subject)
			.withIssuer(properties.getIssuer())
			.withIssuedAt(now)
			.withExpiresAt(expireAt)
			.sign(algorithm);
	}

	public DecodedJWT verifyToken(String token) {
		try {
			return verifier.verify(token);
		} catch (JWTVerificationException e) {
			return null;
		}
	}
}


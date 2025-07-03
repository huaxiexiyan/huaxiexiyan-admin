package com.huaxiexiyan.erp.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiyan
 * @date 2025/7/1 18:11
 */
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
	private String secret;
	private String issuer;
	private long expireSeconds;

	// Getters & Setters
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public long getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(long expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
}

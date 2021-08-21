package com.health.talan.message.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
 
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private String username;
  private Collection authorities;
  private Long id;
 
  public JwtResponse(String accessToken, String username, Collection authorities, Long id) {
    this.token = accessToken;
    this.username = username;
    this.authorities = authorities;
    this.id = id;
  }
 
  public String getAccessToken() {
    return token;
  }
 
  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }
 
  public String getTokenType() {
    return type;
  }
 
  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }
 
  public String getUsername() {
    return username;
  }
 
  public void setUsername(String username) {
    this.username = username;
  }
  
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
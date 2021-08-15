package com.health.talan.message.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
 
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private String username;
  private Collection authorities;
 
  public JwtResponse(String accessToken, String username, Collection authorities) {
    this.token = accessToken;
    this.username = username;
    this.authorities = authorities;
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
}
package com.fatechnologies.security.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Component;

@Component
public class AccountDetailsPasswordService implements UserDetailsPasswordService {

  @Override
  public UserDetails updatePassword(UserDetails user, String newPassword) {
    return null;
  }
}

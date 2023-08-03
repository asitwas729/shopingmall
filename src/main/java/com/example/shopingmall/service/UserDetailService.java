package com.example.shopingmall.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailService {
  UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException;


}

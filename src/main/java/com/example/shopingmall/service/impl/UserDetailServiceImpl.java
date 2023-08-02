package com.example.shopingmall.service.impl;

import com.example.shopingmall.repository.UserRepository;
import com.example.shopingmall.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    return userRepository.getByUserId(userName);
  }
}

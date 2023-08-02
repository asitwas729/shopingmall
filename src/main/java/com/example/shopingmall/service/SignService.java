package com.example.shopingmall.service;

import com.example.shopingmall.dto.SignInResultDto;
import com.example.shopingmall.dto.SignUpResultDto;

public interface SignService {

  SignUpResultDto UserSignUp(String userId, String password, String userName, String email, String phone);

  SignInResultDto signIn(String id, String password) throws RuntimeException;

}

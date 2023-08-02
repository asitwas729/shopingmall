package com.example.shopingmall.service.impl;

import java.util.Collections;

import com.example.shopingmall.config.security.JwtTokenProvider;
import com.example.shopingmall.dto.CommonResponse;
import com.example.shopingmall.dto.SignInResultDto;
import com.example.shopingmall.dto.SignUpResultDto;
import com.example.shopingmall.entity.User;
import com.example.shopingmall.repository.UserRepository;
import com.example.shopingmall.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {

  public UserRepository userRepository;
  public JwtTokenProvider jwtTokenProvider;
  public PasswordEncoder passwordEncoder;

  @Autowired
  public SignServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.jwtTokenProvider = jwtTokenProvider;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public SignUpResultDto UserSignUp(String userId, String password, String userName, String email, String phone) {
    System.out.println("[signUp] 회원가입");
    User user;
    user = User.builder().userId(userId).userName(userName).email(email).phone(phone).type("general")
          .password(passwordEncoder.encode(password)).roles(Collections.singletonList("ROLE_USER")).build();

    User savedUser = userRepository.save(user);
    SignUpResultDto signUpResultDto = new SignUpResultDto();
    if(!savedUser.getUsername().isEmpty()) {
      setSuccessResult(signUpResultDto);
    } else {
      setFailResult(signUpResultDto);
    }
    return signUpResultDto;
  }


  @Override
  public SignInResultDto signIn(String id, String password) throws RuntimeException {
    User user = userRepository.getByUserId(id);
    if(!passwordEncoder.matches(password, user.getPassword())) {
      throw new RuntimeException();
    }

    SignInResultDto signInResultDto = SignInResultDto.builder().token(jwtTokenProvider.createToken(String.valueOf(user.getUserId()), user.getRoles())).build();
    setSuccessResult(signInResultDto);

    return signInResultDto;
  }

  private void setSuccessResult(SignUpResultDto result) {
    result.setSuccess(true);
    result.setCode(CommonResponse.SUCCESS.getCode());
    result.setMsg(CommonResponse.SUCCESS.getMsg());
  }
  private void setFailResult(SignUpResultDto result) {
    result.setSuccess(false);
    result.setCode(CommonResponse.FAIL.getCode());
    result.setMsg(CommonResponse.FAIL.getMsg());
  }
}
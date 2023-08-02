package com.example.shopingmall.controller;

import com.example.shopingmall.dto.SignInResultDto;
import com.example.shopingmall.dto.SignUpResultDto;
import com.example.shopingmall.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-api")
public class SignController {

  private final SignService signService;

  @Autowired
  public SignController(SignService signService) {
    this.signService = signService;
  }

  @PostMapping("/sign-in")
  @PreAuthorize("hasAnyRole('ROLE_USER')")
  public SignInResultDto UserSignIn(@RequestParam String id, @RequestParam String password) throws RuntimeException {
    SignInResultDto signInResultDto = signService.signIn(id, password);
    if(signInResultDto.getCode() == 0) {
      System.out.println("[SignIn] 정상적으로 로그인되었습니다. "+ signInResultDto.getToken());
    }
    return signInResultDto;
  }
  @PostMapping("/sign-up")
  public SignUpResultDto UserSignUp(@RequestParam String id,@RequestParam String phone, @RequestParam String password, @RequestParam String name, @RequestParam String email) throws RuntimeException {
    SignUpResultDto signUpResultDto = signService.UserSignUp(id, password, name,phone,email);
    return signUpResultDto;
  }

  @GetMapping("/exception")
  public void exception() throws RuntimeException {
    throw new RuntimeException("접근이 금지되었습니다.");
  }
}

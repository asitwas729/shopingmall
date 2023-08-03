package com.example.shopingmall.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "order")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String userId;

  @NotNull
  private String address1;  //우편번호

  @NotNull
  private String address2;  //주소

  private String address3;  //상세주소

  private String receiverName;

  private String receiverPhone;

  private LocalDateTime orderDate;

  private String status;
  //환불하였을때 상태를 알수있게끔
}

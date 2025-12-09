package com.example.demoMakeBoard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
public class PasswordForm {
  @NotBlank(message="기존 패스워드를 입력해주세요")
  private String old;
  @NotBlank(message="새 패스워드를 입력해주세요")
  private String password;
  @Size(message="8자 이상 입력해주세요")
  @NotBlank(message="새 패스워드를 확인해주세요")
  private String passwordConfirm;
}

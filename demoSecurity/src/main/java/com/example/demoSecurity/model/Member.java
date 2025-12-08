package com.example.demoSecurity.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Member {
  private Long id;
  private String name;
  private String email;
}

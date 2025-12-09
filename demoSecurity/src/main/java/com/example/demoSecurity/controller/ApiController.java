package com.example.demoSecurity.controller;

import com.example.demoSecurity.model.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {
  private List<Member> members = List.of(
          new Member(1L,"홍길동", "GH@gmail.com", "test1234"),
          new Member(2L,"이몽룡", "ML@gmail.com", "test1234"),
          new Member(3L,"성춘향", "CH@gmail.com", "test1234"),
          new Member(4L,"황진이", "JI@gmail.com", "test1234")
  );

  @GetMapping("api/members")
  public List<Member> getMembers(){
    return members;
  }
}

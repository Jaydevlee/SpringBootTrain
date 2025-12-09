package com.example.demoSecurity.controller;

import com.example.demoSecurity.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
@Slf4j
public class MemberController {
  private final List<Member> members = List.of(
          new Member(1L,"홍길동", "GH@gmail.com", "test1234"),
          new Member(2L,"이몽룡", "ML@gmail.com", "test1234"),
          new Member(3L,"성춘향", "CH@gmail.com", "test1234"),
          new Member(4L,"황진이", "JI@gmail.com", "test1234")
  );

  @GetMapping("member/list")
  public String getMembers(Model model){
    model.addAttribute("members", members);
    return "member-list";
  }
}

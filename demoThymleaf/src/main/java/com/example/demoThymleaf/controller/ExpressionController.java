package com.example.demoThymleaf.controller;


import com.example.demoThymleaf.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ExpressionController {
  private final List<Member> members = List.of(
          new Member("홍길동", "GH@gmail.com", 20),
          new Member("이몽룡", "ML@gmail.com", 25),
          new Member("성춘향", "CH@gmail.com", 27),
          new Member("황진이", "JI@gmail.com", 23)
  );
  //GET object 호출 시 컨트롤러에 정의된 회원 목록 중 첫 번째 객체를 member라는 이름 으로 모델에 저장 하고 출력
  @GetMapping("/object")
  public String getMember(Model model){
    model.addAttribute("member", members.getFirst());
    //템플릿 파일의 이름 반환
    return "expression/object";
  }

  @GetMapping("/calendars")
  public String getCalendars(Model model){
    Date date = Calendar.getInstance().getTime();
    model.addAttribute("date", date);
    return "expression/calendars";
  }

  @GetMapping("/numbers")
  public String getNumbers(Model model){
    model.addAttribute("num1", 345620.5226);
    model.addAttribute("num2", 3502340);
    return "expression/numbers";
  }

  @GetMapping("/condition")
  public String getCondition(Model model){
    model.addAttribute("showWelcome", true);
    model.addAttribute("showDescription", false);
    return "expression/condition";
  }

  @GetMapping("/loop")
  public String getMemberList(Model model){
    model.addAttribute("members", members);
    return "expression/loop";
  }

  @GetMapping("/link")
  public String getLink(Model model){
    model.addAttribute("id", 3);
    return "expression/link";
  }

  @GetMapping("/member")
  public String getMemberByIdParam(@RequestParam(name="id", required = false) Integer id, Model model){
    if(id != null) {
      model.addAttribute("member", members.get(id));
    } else {
      model.addAttribute("member", members.getFirst());
    }
    return "expression/member";
  }

  @GetMapping("/member/{id}")
  public String getMemberByIdPath(@PathVariable("id") Integer id, Model model){
    model.addAttribute("member", members.get(id));
    return "expression/member";
  }
}

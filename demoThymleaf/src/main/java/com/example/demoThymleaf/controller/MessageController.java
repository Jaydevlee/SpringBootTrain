package com.example.demoThymleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MessageController {
  //url /message/basic을 처리
  @GetMapping("/message/basic")
  public String getMessageBasic() {
    return "message/message-basic";
  }

  @GetMapping("message/customer")
  public String getMessageCustomer(Model model){
    model.addAttribute("name", "한빛미디어");
    model.addAttribute("phone", "02-325-0384");
    return "message/message-customer";
  }
}



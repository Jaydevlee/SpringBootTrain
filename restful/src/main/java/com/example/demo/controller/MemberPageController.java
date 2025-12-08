package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 페이지(화면)용
@Controller
public class MemberPageController {
    @GetMapping("/members")
    public String membersPage() {
        return "members";   // templates/members.html
    }
}


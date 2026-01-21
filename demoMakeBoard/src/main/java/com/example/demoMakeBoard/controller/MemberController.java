package com.example.demoMakeBoard.controller;

import com.example.demoMakeBoard.dto.MemberDto;
import com.example.demoMakeBoard.dto.MemberForm;
import com.example.demoMakeBoard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  @GetMapping("/list")
  public String getMemberList(@PageableDefault(size = 10, sort="id", direction=Sort.Direction.DESC)
                                Pageable pageable, Model model) {
    Page<MemberDto> page = memberService.findAll(pageable);
    model.addAttribute("page", page);
    return "member-list";
  }

  @GetMapping("/edit")
  public String getMemberEdit(@RequestParam("id") Long id,
                              @ModelAttribute("member")MemberForm memberForm) {
    MemberDto memberDto = memberService.findById(id);
    memberForm.setId(memberDto.getId());
    memberForm.setName(memberDto.getName());
    memberForm.setEmail(memberDto.getEmail());
    return "member-edit";
  }

  @PostMapping("/edit")
  public String patchMember(@ModelAttribute("member")MemberForm memberForm,
                           BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "member-edit";
    }
    memberService.patch(memberForm);
    return "redirect:/member/list";
  }

  @GetMapping("/delete")
  public String deleteMember(@RequestParam("id") Long id) {
    memberService.delete(id);
    return "redirect:/member/list";
  }
}

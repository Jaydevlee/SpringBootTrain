package com.example.demoRestful.controller;

import com.example.demoRestful.dto.MemberRequest;
import com.example.demoRestful.dto.MemberResponse;
import com.example.demoRestful.model.Member;
import com.example.demoRestful.repository.MemberRepository;
import com.example.demoRestful.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //RESTful API를 제공하는 컨트롤러 의미를 담은 애노테이션
@RequestMapping("/api/members") //이 컨트롤러가 괄호안에 정의된 URI로 시작하는 모든 클라이언트의 요청 처리
@RequiredArgsConstructor //MemberRepository 객체를 생성자를 통해 주입
public class MemberController {
    private final MemberService memberService; //클래스내부에 MemberSerive선언

    //HTTP 메소드 중 하나인 post로 전달된 요청

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //Http상태코드 전달
    //MemberRequest DTO 객체를 받아 서비스 객체를 통해 회원생성 후 MemberResponseDTO 객체를 반환
    public MemberResponse post(@RequestBody MemberRequest memberRequest){
      return memberService.create(memberRequest);
    }

    @GetMapping
    public List<MemberResponse> getAll(){
      return memberService.findAll();
    }

    @GetMapping("/{id}")
    public MemberResponse getById(@Param("id") Long id){
      return memberService.findById(id);
    }
}

package com.example.demoMakeBoard.service;

import com.example.demoMakeBoard.dto.MemberDto;
import com.example.demoMakeBoard.model.Member;
import com.example.demoMakeBoard.repository.ArticleRepository;
import com.example.demoMakeBoard.repository.AuthorityRepository;
import com.example.demoMakeBoard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final AuthorityRepository authorityRepository;
  private final ArticleRepository articleRepository;

  public MemberDto findById(Long id) {
    return memberRepository.findById(id).map(this::mapToMemberDto).orElseThrow();
  }

  public MemberDto mapToMemberDto(Member member){
    return MemberDto.builder()
            .id(member.getId())
            .name(member.getName())
            .email(member.getEmail())
            .build();
  }
}

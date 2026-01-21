package com.example.demoMakeBoard.service;

import com.example.demoMakeBoard.dto.MemberDto;
import com.example.demoMakeBoard.dto.MemberForm;
import com.example.demoMakeBoard.model.Member;
import com.example.demoMakeBoard.repository.ArticleRepository;
import com.example.demoMakeBoard.repository.AuthorityRepository;
import com.example.demoMakeBoard.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final AuthorityRepository authorityRepository;
  private final ArticleRepository articleRepository;
  private final MemberForm memberForm;
  private final PasswordEncoder passwordEncoder;

  public MemberDto findById(Long id) {
    return memberRepository.findById(id).map(this::mapToMemberDto).orElseThrow();
  }

  public MemberDto create(MemberForm memberForm) {
    Member member = Member.builder()
            .name(memberForm.getName())
            .password(passwordEncoder.encode(memberForm.getPassword()))
            .email(memberForm.getEmail()).build();
    memberRepository.save(member);
    return mapToMemberDto(member);
  }

  public Optional<MemberDto> findByEmail(String email) {
    return memberRepository.findByEmail(email).map(this::mapToMemberDto);
  }

  public MemberDto mapToMemberDto(Member member) {
    return MemberDto.builder()
            .id(member.getId())
            .name(member.getName())
            .email(member.getEmail())
            .build();
  }

  public boolean checkPassword(Long id, String password) {
    Member member = memberRepository.findById(id).orElseThrow();
    return passwordEncoder.matches(password, member.getPassword());
  }

  public void updatePassword(Long id, String password) {
    Member member = memberRepository.findById(id).orElseThrow();
    member.setPassword(passwordEncoder.encode(password));
    memberRepository.save(member);
  }

  public Page<MemberDto> findAll(Pageable pageable) {
    return memberRepository.findAll(pageable).map(this::mapToMemberDto);
  }

  public MemberDto patch(MemberForm memberForm) {
    Member member = memberRepository.findById(memberForm.getId()).orElseThrow();
    if (memberForm.getName() != null) {
      member.setName(memberForm.getName());
    }
    if (memberForm.getPassword() != null) {
      member.setPassword(memberForm.getPassword());
    }
    if (memberForm.getEmail() != null) {
      member.setEmail(memberForm.getEmail());
    }
    memberRepository.save(member);
    return mapToMemberDto(member);
  }
  @Transactional
  public void delete(Long id) {
    Member member = memberRepository.findById(id).orElseThrow();
    articleRepository.deleteAllByMember(member);
    memberRepository.delete(member);
  }
}

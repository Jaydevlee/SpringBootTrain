package com.example.demoRestful.service;

import com.example.demoRestful.dto.MemberRequest;
import com.example.demoRestful.dto.MemberResponse;
import com.example.demoRestful.model.Member;
import com.example.demoRestful.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private MemberResponse mapToMemberResponse(Member member) {
      return MemberResponse.builder()
              .id(member.getId())
              .name(member.getName())
              .email(member.getEmail())
              .age(member.getAge())
              .build();
    }

    public MemberResponse create(MemberRequest memberRequest) {
        Member member= Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .age(memberRequest.getAge())
                .enabled(true).build();
        //생성된 Member 도메인 객체를 저장
        memberRepository.save(member);
        //MemberResponseDTO 객체로 변환
        return mapToMemberResponse(member);
    }
    public List<MemberResponse> findAll(){
        return memberRepository
                .findAll()
                //findAll()로 가져온 리스테에서 사용자 모델 객체를 하나씩 스트림으로 보내고
                .stream()
                //MemberResponseDTO로 매핑 후
                .map(this::mapToMemberResponse)
                //다시 리스트로 만들기
                .toList();
    }
}

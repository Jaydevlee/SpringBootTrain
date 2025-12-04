package com.example.demo_springDataJdbc;

import com.example.demo_springDataJdbc.model.Member;
import com.example.demo_springDataJdbc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JpaApplication implements ApplicationRunner {
    private final MemberRepository memberRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
      memberRepository.save(Member.builder()
                      .name("윤서준")
                      .email("SeojunYoon@hanbit.co.kr")
                      .age(10).build());
      memberRepository.save(Member.builder()
                      .name("윤광철")
                      .email("KwangcheolYoon@hanbit.co.kr")
                      .age(43).build());
  }
}

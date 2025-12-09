package com.example.demoSecurity.config;

import com.example.demoSecurity.model.Authority;
import com.example.demoSecurity.model.Member;
import com.example.demoSecurity.repository.AuthorityRepository;
import com.example.demoSecurity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
  private final MemberRepository memberRepository;
  private final AuthorityRepository authorityRepository;
  private final PasswordEncoder passwordEncoder;


  @Override
  public void run(ApplicationArguments args) throws Exception {
    if(memberRepository.count() == 0){
      Member user=memberRepository.save(Member.builder()
              .name("홍길동")
              .email("hong@gmail.com")
              .password(passwordEncoder.encode("password")).build());
      Member admin=memberRepository.save(Member.builder()
              .name("이몽룡")
              .email("lee@gmail.com")
              .password(passwordEncoder.encode("password")).build());
      authorityRepository.save(Authority.builder().authority("ROLE_USER").member(user).build());
      authorityRepository.save(Authority.builder().authority("ROLE_ADMIN").member(admin).build());
    }
  }


}

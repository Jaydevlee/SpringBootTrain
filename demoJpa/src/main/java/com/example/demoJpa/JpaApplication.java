package com.example.demoJpa;

import com.example.demoJpa.model.Article;
import com.example.demoJpa.model.Member;
import com.example.demoJpa.repository.ArticleRepository;
import com.example.demoJpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Component
@Slf4j
@RequiredArgsConstructor
public class JpaApplication implements ApplicationRunner {
  private final MemberRepository memberRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    var member1 = Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").age(10).build();
    log.info("save 윤서준");
    memberRepository.save(member1); //save메소드 insert
    log.info("save{}", member1);

    var member2 = Member.builder().name("윤광철").email("KwangcheolYoon@hanbit.co.kr").age(43).build();
    log.info("save 윤광철");
    memberRepository.save(member2);
    log.info("save{}", member2);

    log.info("read 윤서준");
    member1 = memberRepository.findById(member1.getId()).orElseThrow();

    log.info("update 윤서준");
    member1.setAge(11); //save메소드 update
    memberRepository.save(member1);


    log.info("updated {}", member1);

    log.info("update 윤광철");
    memberRepository.save(member2);
    log.info("updated {}", member2);

    log.info("애플리케이션 종료");
  }
}

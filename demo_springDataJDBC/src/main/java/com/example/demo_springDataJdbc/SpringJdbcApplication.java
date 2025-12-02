package com.example.demo_springDataJdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
//               구현클래스                      인터페이스
public class SpringJdbcApplication implements ApplicationRunner {
    //싱글톤
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws  Exception{
        //create
        //Member member = Member.builder().name("정혁").email("HyeokJung@hanbit.co.kr").age(10).build();
        //memberRepository.save(member);

        //update
        //member.setAge(11);
       //memberRepository.save(member);

        //findAll select결과가 여러건일 때
       var members = memberRepository.findAll();
       log.info("{}", members);
       //findById() select 결과가 한건일 때
        var member = memberRepository.findById(1L);
        log.info("{}", member);
    }
}

package com.example.demo_springDataJdbc;

import com.example.demo_springDataJdbc.mapper.MemberMapper;
import com.example.demo_springDataJdbc.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MyBatisApplication implements ApplicationRunner {
    private final MemberMapper memberMapper;


  @Override
  public void run(ApplicationArguments args) throws Exception {
    int count = memberMapper.selectAllCount();
    //log: 콘솔 출력 레벨을 지정할 수 있다. application.properties에서 설정할 수 있다.
    log.info("Member count: {}", count);

    Member member = memberMapper
            .selectByEmail("HyeokJung@hanbit.co.kr")
            .orElseThrow();
    log.info("Member: {}", member);
  }
}

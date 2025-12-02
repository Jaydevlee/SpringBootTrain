package com.example.demo_springDataJdbc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 애플리케이션을 시작할 때 리포지터리 객체를 스프링 빈 객체로 등록해 피요한 곳에서 의존성으로 주입 받아 사용
public interface MemberRepository extends CrudRepository<Member, Long>  {
    //<Member, Long> 제네릭 타입
    //기본적인 CRUD메소드 (생성 및 수정: save, 조회:findById findAll, 삭제: deleteById) 자동 제공
    List<Member> findByName(String name);
    List<Member> findByEmail(String email);
    List<Member> findByNameContaining(String name);

    List<Member> findByNameAndEmail(String name, String email);
    List<Member> findByNameOrEmail(String name, String email);

    List<Member> findByAgeGreaterThan(int age);
    List<Member> findByAgeLessThan(int age);
    List<Member> findByAgeBetween(int min, int max);
}

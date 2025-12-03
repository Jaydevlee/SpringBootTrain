package com.example.demo_springDataJdbc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 애플리케이션을 시작할 때 리포지터리 객체를 스프링 빈 객체로 등록해 피요한 곳에서 의존성으로 주입 받아 사용
public interface MemberRepository extends CrudRepository<Member, Long>  {
    //<Member, Long> 제네릭 타입 -> Member 객체를 DB와 연동할 것이며 이 객체의 Primary Key 타입은 Long 이다.
    //기본적인 CRUD메소드 (생성 및 수정: save, 조회:findById findAll, 삭제: deleteById) 자동 제공

    // 이름과 이메일을 사용하여 조회하는 메소드, 포함여부를 조회 조건으로 정의한 메소드
    List<Member> findByName(String name);
    List<Member> findByEmail(String Email);
    List<Member> findByNameContaining(String name);

    //2개 이상의 프로퍼티를 사용한 검색 조건 표현
    List<Member> findByIdAndEmail(String name, String email);
    List<Member> findByIdOrEmail(String name, String email);

    //숫자로 된 칼럼에 대해 크기를 비교해 조회하는 메소드
    List<Member> findByAgeGreaterThan (int age);
    List<Member> findByAgeLessThan (int age);
    List<Member> findByAgeBetween (int min, int max);
}

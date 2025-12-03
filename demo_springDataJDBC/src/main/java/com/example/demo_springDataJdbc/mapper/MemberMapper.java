package com.example.demo_springDataJdbc.mapper;

import com.example.demo_springDataJdbc.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;
@Mapper
public interface MemberMapper {
    List<Member> selectAll(); //select 된 결과가 여러개 인 경우(연동)
    Optional<Member> selectById(@Param("id") Long id); //id를 조건으로 select 결과가 1개만 나온다.(select 결과가 1개인 경우)
    Optional<Member> selectByEmail(@Param("email") String email); //email을 조건으로 select 결과가 1개만 나온다.
    List<Member> selectAllOrderByAgeAsc(); //나이가 적은 순서 대로 select 결과를 표시
    //select된 결과가 여러개 인 경우 정렬 기준을 사용자가 선택
    List<Member> selectAllOrderBy(@Param("order") String order, @Param("dir") String dir);
    //select된 결과가 여러개 인 경우 이름으로 검색
    List<Member> selectByNameLike(@Param("name") String name);
    //select한 결과에 대한 건수
    int selectAllCount();
    int insert(@Param("member") Member member);
    //int insert(@Param("name") String name, @Param("email") String email, @Param("age") int age);
    //바이트 수로 인한 메모리 관리, 보안 측면을 고려하여 Member 클래스를 매개변수로 지정하는 것을 선호,
    int update(@Param("member") Member member);
    int delete(@Param("member") Member member); //Member클래스 조건으로 탈퇴
    int deleteById(@Param("id") Long id); //id를 조건으로 탈퇴
    int deleteAll();
}

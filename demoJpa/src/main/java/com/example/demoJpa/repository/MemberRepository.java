package com.example.demoJpa.repository;

import com.example.demoJpa.model.MemberStats;
import com.example.demoJpa.model.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  //이름으로 회원 조회
  List<Member> findAllByName(String name);
  List<Member> findByName(String name);
  List<Member> findByNameIs(String name);
  List<Member> findByNameEquals(String name);

  //find 이외에도 get, query, read, search 사용 가능
  List<Member> getByName(String name);
  List<Member> queryByName(String name);
  List<Member> readByName(String name);
  List<Member> searchByName(String name);

  //이름 이메일을 and 조건으로 조회
  List<Member> findByNameAndEmail(String name, String email);
  //이름 이메일을 or 조건으로 조회
  List<Member> findByNameOrEmail(String name, String email);
  //이름 시작으로 회원 조회
  List<Member> findByNameStartingWith(String name);
  //이름 마지막으로 회원 조회
  List<Member> findByNameEndingWith(String name);
  //이름을 포함하는 회원 조회
  List<Member> findByNameContaining(String name);
  //이름을 포함하는 회원 조회로 LIKE 검색을 위한 와일드카드 %를 매개변수에 포함
  List<Member> findByNameLike(String name);

  //나이 정보가 존재하지 않는 회원 조회
  List<Member> findByAgeIsNull();

  //나이 정보가 존재하는 회원 조회
  List<Member> findByAgeIsNotNull();

  //매개변수로 전달된 나이로 회원 조회
  List<Member> findByAgeIs(int age);

  //매개변수로 전달된 나이보다 나이거 더 많은 회원 조회
  List<Member> findByAgeGreaterThan(int age);
  List<Member> findByAgeAfter(int age);

  //매개변수로 전달된 나이보다 나이가 더 많거나 같은 회원
  List<Member> findByAgeGreaterThanEqual(int age);

  //매개변수로 전달된 나이보다 나이거 더 적은 회원 조회
  List<Member> findByAgeLessThan(int age);
  List<Member> findByAgeBefore(int age);

  //매개변수로 전달된 나이보다 나이가 더 적거나 같은 회원
  List<Member> findByAgeLessThanEqual(int age);

  //매개변수로 전달된 나이를 포함해 그 사이 나이의 회원 조회
  List<Member> findByAgeBetween(int min, int max);

  //이름순으로 조회
  List<Member> findByOrderByNameAsc();
  //이름 역순 으로 조회
  List<Member> findByOrderByNameDesc();
  //이름 순으로 조회하고 이름이 같은 경우에는 나이의 역순으로 조회
  List<Member> findByOrderByNameAscAgeDesc();
  //이름의 일부로 검색하고 그 결과는 이름순으로 조회
  List<Member> findByNameContainingOrderByNameAsc(String name);

  //이메일을 사용해 회원  삭제
  @Transactional
  int deleteByEmail(String email);

  //이름을 사용해 회원 삭제
  @Transactional
  int deleteByName(String name);

  //JPQL
  @Query("SELECT m FROM Member m WHERE m.name = :name")
  List<Member> findMember(@Param("name") String name);

  @Query("SELECT m FROM Member m WHERE m.name = :name AND m.email = :email")
  List<Member> findMember(@Param("name") String name, @Param("email") String email);

  //JPQL을 활용하여 리포지터리 메소드 정의
  @Query("""
          SELECT m.name, m.email, COUNT(a.id) as count
          FROM Member m LEFT JOIN Article a on a.id = m.id
          GROUP BY m.name, m.email ORDER BY COUNT(a.id) DESC
          """) //nativeQuery: 특정 DB에서만 지원하는 문법이 필요한 경우라면 사용
        List<Object[]> getMemberStatsQbject();

  @Query("""
       SELECT new com.example.demoJpa.model.MemberStats(m.name, m.email, COUNT(a.id))
       FROM Member m
       LEFT JOIN Article a ON a.member = m
       GROUP BY m.name, m.email
       ORDER BY COUNT(a.id) DESC
       """)
  List<MemberStats> getMemberStats();



  @Query(value = """
          SELECT m.name, m.email, COUNT(a.id) as count
          FROM Member m LEFT JOIN Article a on a.id = m.id
          GROUP BY m.name, m.email ORDER BY COUNT(a.id) DESC
          """, nativeQuery = true) //nativeQuery: 특정 DB에서만 지원하는 문법이 필요한 경우라면 사용
  List<Object[]> getMemberStatsNativeQbject();

  @Query(value="""
          SELECT new com.example.demo.model.MemberStats(m.name, m.email, COUNT(a.id) as count)
          FROM Member m LEFT JOIN Article a ON a.member = m
          GROUP BY m ORDER BY count DESC
          """, nativeQuery = true)
  List<MemberStats> getMemberStatsNative();


}

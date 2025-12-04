package com.example.demoJpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="member", indexes = {
        @Index(name="idx_name_age", columnList = "display_name, age"),
        @Index(name="idx_email", columnList = "email")
}) //클래스명과 테이블 명을 매핑과 인덱스 생성
@Data // Getter, Setter, ToString 3개의 애노테이션을 @Data로 대체
@Builder // 객체 생성을 위해 빌더패턴을 사용
@AllArgsConstructor // 모든 프로퍼티를 매개변수로 받는 생성자를 자동으로 생성
@NoArgsConstructor // 기본 생성자를 자동으로 생성
public class Member {
  @Id
  //교재와 달리 시퀀스 구문을 따로 적은 이유: 교재는 mysql사용하고 우리는 Oracle 사용
  @SequenceGenerator(name="member_seq_gen", //JPA 안에서 부를 이름
          sequenceName = "mem_seq", // DB에 실제 존재하는 시퀀스 이름
          allocationSize = 1 )
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
  private Long id;
  //칼럼이름, 길이 지정, NULL비허용, 유니크 조건 추가
  @Column(name="display_name", length = 2048, nullable = false, unique = true)
  private String name;
  @Column(name="email")
  private String email;
  //칼럼이름 지정, NULL 비허용, 기본값 10 지정
  @Column(name="age", nullable = false, columnDefinition = "INTEGER DEFAULT 10")
  private int age;
}

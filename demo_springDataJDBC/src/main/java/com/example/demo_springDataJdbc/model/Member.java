package com.example.demo_springDataJdbc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table //클래스명과 테이블 명을 매핑
@Data // Getter, Setter, ToString 3개의 애노테이션을 @Data로 대체
@Builder // 객체 생성을 위해 빌더패턴을 사용
@AllArgsConstructor // 모든 프로퍼티를 매개변수로 받는 생성자를 자동으로 생성
@NoArgsConstructor // 기본 생성자를 자동으로 생성
public class Member {
    @Id
    @SequenceGenerator(name="member_seq_gen", //JPA 안에서 부를 이름
                       sequenceName = "MEM_SEQ", // DB에 실제 존재하는 시퀀스 이름
                       allocationSize = 1 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
    private Long id;
    private String name;
    private String email;
    private int age;
}

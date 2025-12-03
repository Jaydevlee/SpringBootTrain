package com.example.demo_springDataJdbc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table //클래스명과 테이블 명을 매핑
@Data // Getter, Setter, ToString 3개의 애노테이션을 @Data로 대체
@Builder // 객체 생성을 위해 빌더패턴을 사용
@AllArgsConstructor // 모든 프로퍼티를 매개변수로 받는 생성자를 자동으로 생성
@NoArgsConstructor // 기본 생성자를 자동으로 생성
public class Member {
    @Id
    /*Spring Data JDBC에서 @Id의 역할
    * 해당 필드가 테이블의 기본키(Primary Key)임을 표시
    * save()시 insert, update구별 - 기본키가 null이면 insert, null이 아니면 update
    */
    private Long id;
    private String name;
    private String email;
    private int age;
}

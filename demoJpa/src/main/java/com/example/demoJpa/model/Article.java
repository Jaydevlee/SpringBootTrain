package com.example.demoJpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)  // 생성시간과 수정시간을 자동으로 관리하는 엔티티 객체를 정의
public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; //게시물 번호
  private String title; //제목
  private String description; // 내용
  //관계 매핑
  //@OneToOne: 1 대 1
  //@OntToMany: 1 대 다
  //@ManyToOne: 다 대 1
  //@ManyToMany: 다 대 다
  @ManyToOne
  @JoinColumn(name="member_id") //member_id를 외래키로 하여 Member테이블을 참조하여 관리되는 article 객체
  private Member member;
}

package com.example.demoMyBastis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
  private Long id; //게시물 번호
  private String title; //제목
  private String description; // 내용
  private Date Created; // 작성일
  private Date updated; //수정일
  private Long memberId; //글쓴이
}

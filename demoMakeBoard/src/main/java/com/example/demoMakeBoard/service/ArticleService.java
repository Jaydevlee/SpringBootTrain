package com.example.demoMakeBoard.service;

import com.example.demoMakeBoard.dto.ArticleDto;
import com.example.demoMakeBoard.dto.MemberDto;
import com.example.demoMakeBoard.model.Article;
import com.example.demoMakeBoard.repository.ArticleRepository;
import com.example.demoMakeBoard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
  private final MemberRepository memberRepository;
  private final ArticleRepository articleRepository;

  public ArticleDto mapToArticleDto(Article article){
    return ArticleDto.builder()
            .id(article.getId())
            .title(article.getTitle())
            .description(article.getDescription())
            .created(article.getCreated())
            .updated(article.getUpdated())
            .name(article.getMember().getName())
            .email(article.getMember().getEmail())
            .build();
  }
}

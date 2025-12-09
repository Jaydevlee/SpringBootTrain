package com.example.demoMakeBoard.repository;

import com.example.demoMakeBoard.model.Article;
import com.example.demoMakeBoard.model.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ArticleRepository extends JpaRepository<Article, Long> {
  @Transactional
  void deleteAllByMember(Member member);
}

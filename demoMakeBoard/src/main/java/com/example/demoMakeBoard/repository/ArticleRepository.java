package com.example.demoMakeBoard.repository;

import com.example.demoMakeBoard.model.Member;
import jakarta.transaction.Transactional;

public interface ArticleRepository {
  @Transactional
  void deleteAllByMember(Member member);
}

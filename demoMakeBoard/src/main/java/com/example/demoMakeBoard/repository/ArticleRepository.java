package com.example.demoMakeBoard.repository;

import com.example.demoMakeBoard.model.Member;
import jakarta.transaction.Transactional;

  @Transactional
  void deleteAllByMember(Member member);
}

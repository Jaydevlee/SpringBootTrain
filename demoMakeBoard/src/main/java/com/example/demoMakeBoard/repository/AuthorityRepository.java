package com.example.demoMakeBoard.repository;

import com.example.demoMakeBoard.model.Authority;
import com.example.demoMakeBoard.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
  List<Authority> findByMember(Member member);
}

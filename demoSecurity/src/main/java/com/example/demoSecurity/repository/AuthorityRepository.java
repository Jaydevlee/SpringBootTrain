package com.example.demoSecurity.repository;

import com.example.demoSecurity.model.Authority;
import com.example.demoSecurity.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
  List<Authority> findByMember(Member member);
}

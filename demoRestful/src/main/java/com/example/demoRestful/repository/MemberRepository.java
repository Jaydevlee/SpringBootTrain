package com.example.demoRestful.repository;

import com.example.demoRestful.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

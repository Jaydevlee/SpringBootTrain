package com.example.demoMakeBoard.model;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
public class MemberUserDetails implements UserDetails {
  //UserDetails 디폴트 구현 getPassword(), getName(), getAuthorities()
  private String username;
  private String password;
  private List<SimpleGrantedAuthority> authorities;
  //Extras
  private String displayName;
  private Long memberId;

  public MemberUserDetails(Member member, List<Authority> authorities){
    this.username = member.getEmail();
    this.password = member.getPassword();
    this.authorities = authorities.stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).toList();
  }
}

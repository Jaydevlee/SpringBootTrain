package com.example.demoSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;


/*설정을 담당하는 클래스에 붙이는 애노테이션, 이 클래스가 빈을 정의하는 설정파일이라고 알려줌
* 싱글톤 보장
* @Configuration이 없으면 자바코드가 그대로 실행 되며 객체를 계속 생성하여 메모리 낭비가 생김
* */
@Configuration
public class SecurityConfiguration {
  @Bean
  public UserDetailsService userDetailsServiceInMemory(){
    UserDetails user = User.builder()
            .username("user")
            //password는 반드시 단방향으로 인코딩되어야 하나 메모리만 단독으로 사용하면
            //{noop}을 붙여서 인코딩 없이 사용할 수 있다.
            .password("password")
            .roles("USER")
            .build();
    UserDetails admin = User.builder()
            .username("admin")
            .password("password")
            .roles("USER", "ADMIN")
            .build();
    return new InMemoryUserDetailsManager(user, admin);
  }

  @Bean
  UserDetailsManager userDetailManagerJdbc(DataSource dataSource){
    return new JdbcUserDetailsManager(dataSource);
  }
  @Bean
  public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
  }
}

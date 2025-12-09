package com.example.demoMakeBoard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer(){
    return new WebSecurityCustomizer() {
      @Override
      public void customize(WebSecurity web) {
        web.ignoring().requestMatchers(
                "/h2-console/**",
                "/css/**",
                "/js/**",
                "/img/**");
      }
      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(withDefaults())
        .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/signup", "/article/list", "/article/content").permitAll()
                .requestMatchers("/member/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated())
        .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll())
        .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home")
                .permitAll());
        return http.build();
      }
    };
  }
}

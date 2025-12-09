package com.example.demoSecurity.config;

import com.example.demoSecurity.model.Authority;
import com.example.demoSecurity.model.Member;
import com.example.demoSecurity.model.MemberUserDetails;
import com.example.demoSecurity.repository.AuthorityRepository;
import com.example.demoSecurity.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import javax.sql.DataSource;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;


/*설정을 담당하는 클래스에 붙이는 애노테이션, 이 클래스가 빈을 정의하는 설정파일이라고 알려줌
* 싱글톤 보장
* @Configuration이 없으면 자바코드가 그대로 실행 되며 객체를 계속 생성하여 메모리 낭비가 생김
* */
@Configuration
public class SecurityConfiguration {
  @Bean
  public UserDetailsService uerDetailsServiceCustom2(MemberRepository memberRepository, AuthorityRepository authorityRepository){
    return new UserDetailsService() {
      @Override
      //loadUserByUsername()메소드는 사용자가 입력한 아이디를 사용해 멤버 리포지터리에서 멤버 객체를 조회하고 권한 리포지터리에서 권한 목록을 조회한 후 확장 정의한
      //MemberUserDetails 객체를 생성해 반환한다.
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Member member = memberRepository.findByEmail(username).orElseThrow();
        List<Authority> authorities = authorityRepository.findByMember(member);
        return new MemberUserDetails(member, authorities);
      }
    };
  }

  /*@Bean
  UserDetailsManager userDetailManagerJdbc(DataSource dataSource){
    return new JdbcUserDetailsManager(dataSource);
  }*/
  @Bean
  public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
  }

  @Bean

  public SecurityFilterChain securityFilterChain(HttpSecurity http, RememberMeServices rememberMeServices,
                                                 CorsConfigurationSource corsConfigurationSource) throws Exception{
    http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/", "/home").permitAll()
            .requestMatchers("/member/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
            .anyRequest().authenticated())
            .rememberMe(remember -> remember.rememberMeServices(rememberMeServices))
            .formLogin(login -> login
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll())
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/home")
                    .permitAll())
            //cors요청 허용하도록 설정
            .cors(cors->cors.configurationSource(corsConfigurationSource))
                    .httpBasic(withDefaults());
    return http.build();
  }

  @Bean
  PersistentTokenRepository persistentTokenRepository(DataSource dataSource){
    JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
    repository.setDataSource(dataSource);
    return repository;
  }
  @Bean
  RememberMeServices rememberMeServices(
          UserDetailsService userDetailsService, PersistentTokenRepository tokenRepository){
    return new PersistentTokenBasedRememberMeServices("myRememberMeKey", userDetailsService, tokenRepository);
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource(){
    CorsConfiguration conf = new CorsConfiguration();
    conf.setAllowedOrigins(Arrays.asList("https://hanbit.co.kr", "https://capmous.co.kr"));
    conf.setAllowedMethods(Arrays.asList("GET", "POST"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", conf);
    return source;
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer(){
    return new WebSecurityCustomizer() {
      @Override
      public void customize(WebSecurity web) {
        web.ignoring().requestMatchers(
                "/h2-console",
                "/css/**",
                "/js/**",
                "/image/**");
      }
    };
  }
}

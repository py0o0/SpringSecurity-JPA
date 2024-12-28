package com.example.apiServer.configuration;

import com.example.apiServer.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailService userService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return webSecurity -> webSecurity.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/static/**"));

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 1. 인증이 필요한 페이지와 아닌 페이지
                .authorizeRequests()
                // 아래 페이지는 인증 필요 x (로그인, 회원가입)
                    .requestMatchers("/login", "/join").permitAll()//허가
                    // 나머지 모든 페이지는 인증필요
                    // 인증 필요!!
                    .anyRequest().authenticated()
                .and()
                // 2. 로그인 페이지(커스텀), 로그인 성공 후 포워딩 페이지등 지정
                .formLogin()
                    .loginPage("/login")    // `커스텀` 로그인 페이지 이동
                    .defaultSuccessUrl("/") // 로그인 성공하면 홈페이지 포워딩
                .and()
                // 3. 로그아웃 처리, 등 추가 처리
                .logout()
                // 로그아웃 요청은 인증이 있을때만 가능하다!! -> URL 지정 x, 기능만 붙이면 어떤 url도 로그아웃이 될수 있음
                    .logoutSuccessUrl("/login")  // 인증 없이는 접근이 않되게 구성-> 다시 로그인
                    .invalidateHttpSession(true) // 세션(서버측에 클라이언트 정보 저장관리 객체) 처리
                .and()
                .userDetailsService(userService)
                // 4. csrf 공격 대응 처리
                .csrf()
                // 활성화 처리되면 => form 태그 밑에 해당 값에 대한 설정이 필요(hidden 타입)
                    .disable()   // 않함!! -> 나중에 활성화!!
                .build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

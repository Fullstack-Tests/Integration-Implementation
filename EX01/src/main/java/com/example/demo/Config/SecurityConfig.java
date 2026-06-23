package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/*
 * [NCS 2-3. 연계 데이터 보안]  (진도 19_SECURITY 참고)
 *  현재는 임시로 "모두 허용" 상태다. 아래 TODO 를 요구사항대로 수정하라.
 */
@Configuration
public class SecurityConfig {

    // TODO: ★학생 작업★ 연계 접근 보안 규칙 구현
    //  - csrf 비활성화 (REST + httpBasic)
    //  - 인가:  POST /api/link/sync → hasRole("ADMIN")
    //          /api/link/**        → authenticated()
    //          그 외                → permitAll()
    //  - 인증: httpBasic 사용
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 임시: 모두 허용 (TODO 에서 위 규칙으로 교체)
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // TODO: 인메모리 사용자 — user(USER) / admin(ADMIN), 비밀번호 "1234"
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("1234")).roles("USER").build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("1234")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}

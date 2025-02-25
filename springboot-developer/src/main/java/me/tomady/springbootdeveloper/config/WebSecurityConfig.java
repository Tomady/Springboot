package me.tomady.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import me.tomady.springbootdeveloper.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final UserDetailService userService;

    // 스프링 시큐리티 기능 비활성화
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests( // 인증, 인가 설정
                        auth -> auth
                                .requestMatchers(   // 특정 요청과 일치하는 url에 대한 액세스를 설정
                                        new AntPathRequestMatcher("/login"),
                                        new AntPathRequestMatcher("/signup"),
                                        new AntPathRequestMatcher("/user")
                                )
                                .permitAll()    // 누구나 접근이 가능하게 설정 / ex) /login, /signup, /user로 요청이 오면 인증/인가 없이 접근 가능
                                .anyRequest()   // 위에서 설정한 url 이외의 요청에 대해서 설정
                                .authenticated()    // 별도의 인가는 필요하지 않지만 인증이 성공된 상태여야 접근 가능
                )
                .formLogin( // 폼 기반 로그인 설정
                        formLogin -> formLogin
                                .loginPage("/login")    // 로그인 페이지 경로 설정
                                .defaultSuccessUrl("/articles") // 로그인 완료 시 이동할 경로 설정
                )
                .logout(    // 로그아웃 설정
                        logout -> logout
                                .logoutSuccessUrl("/login") // 로그아웃 완료 시 이동할 경로 설정
                                .invalidateHttpSession(true)    // 로그아웃 이후 세션 전체 삭제
                )
                .csrf(AbstractHttpConfigurer::disable)  // csrf 비활성화
                .build();
    }

    // 인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // 사용자 정보 서비스 설정
        authProvider.setUserDetailsService(userService);    // 사용자 정보를 가져올 서비스 설정(userService는 UserDetailsService를 상속받는 클래스여야 한다)
        authProvider.setPasswordEncoder(bCryptPasswordEncoder); // 비밀번호를 암호화하기 위한 인코더 설정
        return new ProviderManager(authProvider);
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

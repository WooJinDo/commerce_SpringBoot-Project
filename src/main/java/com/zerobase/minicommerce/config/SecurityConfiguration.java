package com.zerobase.minicommerce.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // 비밀번호 암호화 기법으로 BCryptPasswordEncoder Bean 객체 생성
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 로그인 실패했을 경우
//    @Bean
//    UserAuthenticationFailureHandler getFailureHandler() {
//        return new UserAuthenticationFailureHandler();
//    }

    // 로그인 성공했을 경우
    @Bean
    UserAuthenticationSuccessHandler getSuccessHandler() {
        return new UserAuthenticationSuccessHandler();
    }

    //authenticationManager에 이미 userDetailsService 및 passwordEncoder 생성
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 로그인 후, static 리소스(정적인 자원들)들의 기본위치를 다 가져와서
    // Spring Security 의 필터를 거치지 않고 통과하도록 설정할 수 있다. (/css/**, /js/**, /images/** 등의 경로를 포함)
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //return (web) -> web.ignoring().antMatchers("/favicon.ico", "/files/**");
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // 모든 Http 요청에 대해서 인증 절차를 PASS 시키도록
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
                .authorizeRequests()    // 요청에 의한 보안검사 시작
                .antMatchers(
                        "/",
                        "/member/register",
                        "/api/members", //회원가입 API
                        "/product/**",
                        "/img/**", // 이미지 url path
                        "/error"
                ).permitAll()
                .antMatchers("/admin/**")
                .hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()   // 어떤 요청에도 보안검사를 한다.

                .and()
                .formLogin()   // 보안 검증은 formLogin 방식으로 하겠다.
                .loginPage("/member/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email") // 로그인 폼에서 input태그의 name이 email로 설정되어야 함
                .failureUrl("/member/login/error")
                .successHandler(getSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

                //예외 처리를 할 때, 이동하는 경로
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/error/denied");
        return http.build();
    }

}

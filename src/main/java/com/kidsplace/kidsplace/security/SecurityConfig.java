package com.kidsplace.kidsplace.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()           // 없으면 post요청을 차단한다.

                .authorizeRequests()
                // .antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN")
                // .antMatchers("/ticket/ticketBuy").hasAnyRole("ROLE_USER","ROLE_ADMIN")
                .anyRequest().permitAll()

                // form로그인 형태를 사용할 때 사용할 수도 있는 로직. 현재는 fetch로 기능하기때문에 필요 없다.
                //.and()
                //.formLogin()                                    // formLogin 인증 방식을 사용한다는 의미.
                //.loginPage("/user/login")                     // 인가정보가 없는 사람이 페이지를 요청하면 이쪽을 띄워준다는 의미.
                //.loginProcessingUrl("/user/login")            // 로그인을 처리할 Url을 입력.
                //.usernameParameter("uId")                     // 클라이언트가 전송한 폼 데이터 중 "uId"라는 name을 가진 값을 스프링 시큐리티에서 uId로 사용한다.  즉, UsernamePasswordAuthenticationFilter 에서 Authentication 객체를 생성할때 각각의 변수 값을 사용한다.
                //.passwordParameter("uPassword")               // 클라이언트가 전송한 폼 데이터 중 "uPassword"라는 name을 가진 값을 스프링 시큐리티에서 uPassword로 사용한다.  즉, UsernamePasswordAuthenticationFilter 에서 Authentication 객체를 생성할때 각각의 변수 값을 사용한다.
                //.successHandler(authenticationSuccessHandler) // 로그인 성공시 작동하는 클래스.
                //.failureHandler(authenticationFailureHandler) // 로그인 실패시 작동하는 클래스.

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

        return http.build();
    }

}

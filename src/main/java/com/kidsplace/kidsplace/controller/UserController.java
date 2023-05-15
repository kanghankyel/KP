package com.kidsplace.kidsplace.controller;

import com.kidsplace.kidsplace.commons.UserVO;
import com.kidsplace.kidsplace.security.CustomUserDetailsService;
import com.kidsplace.kidsplace.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    UserService us;

    // 컨트롤러 본 개채의 생성자
    public UserController(CustomUserDetailsService customUserDetailsService, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    // 회원가입 페이지 이동
    @GetMapping("/signup")
    public String signup(){
        return "/user/signup";
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody UserVO userVO){
        try{
            us.signUp(userVO);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception e){
            logger.error("회원가입 정보 저장에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 로그인 페이지 이동
    @GetMapping("/login")
    public String login(){
        return "/user/login";
    }

    // 로그인
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Boolean> login(@RequestBody UserVO userVO){
        try{
            // logger.warn("등록된 회원 정보 확인");
            // logger.warn(userVO.toString());

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userVO.getuId(), userVO.getuPassword());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            boolean result = us.updateVisit(userVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 아이디 중복 체크
    @PostMapping("/uIdCheck")
    public ResponseEntity<Boolean> uIdCheck(@RequestBody HashMap<String, String> map){
        String uId = map.get("uId");
        try{
            if(us.uIdCheck(uId) == 0){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // 마이페이지 페이지 이동
    @GetMapping("/mypage")
    public String mypage(){
        return "/user/mypage";
    }

}

package com.kidsplace.kidsplace.controller;

import com.kidsplace.kidsplace.commons.UserVO;
import com.kidsplace.kidsplace.security.CustomMember;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        return "user/signup";
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
        return "user/login";
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

    // 마이페이지 개인정보 구현
    @GetMapping("/mypage")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String Mypage (Model model
                            , @AuthenticationPrincipal CustomMember customMember){
        String uNum = customMember.getMember().getuNum();
        // System.out.println(customMember.getMember().getuNum());
        List<UserVO> userinfo = us.userInfo(uNum);
        model.addAttribute("user", userinfo);
        return "user/mypage";
    }

    // 마이페이지 수정 페이지 이동
    @GetMapping("/userEdit")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String UserInfo (Model model
                            , @AuthenticationPrincipal CustomMember customMember){
        String uNum = customMember.getMember().getuNum();
        // System.out.println(customMember.getMember().getuNum());
        List<UserVO> userinfo = us.userInfo(uNum);
        model.addAttribute("user", userinfo);
        return "user/userEdit";
    }

    // 회원정보 수정 데이터베이스 반영
    @PostMapping("/userEdit")
    public ResponseEntity<Boolean> userEdit(@RequestBody UserVO userVO){
        try{
            boolean result = us.userEdit(userVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("회원정보 수정에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 회원탈퇴
    @PostMapping("/userDelete")
    public ResponseEntity<Boolean> userDelete(@RequestBody UserVO userVO){
        try{
            boolean result = us.userDelete(userVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("회원탈퇴에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 비밀번호변경 페이지 이동
    @GetMapping("/passwordEdit")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String PasswordEditPage (Model model
                            , @AuthenticationPrincipal CustomMember customMember){
        String uNum = customMember.getMember().getuNum();
        // System.out.println(customMember.getMember().getuNum());
        List<UserVO> userinfo = us.userInfo(uNum);
        model.addAttribute("user", userinfo);
        return "user/passwordEdit";
    }

    // 비밀번호 변경
    @PostMapping("/passwordEdit")
    public ResponseEntity<Boolean> PasswordEdit(@RequestBody UserVO userVO){
        try{
            boolean result = us.PasswordEdit(userVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("비밀번호 변경에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 아이디 찾기 페이지 이동
    @GetMapping("/findId")
    public String findIdPage(Model model) {
        model.addAttribute("uId", ""); // 초기에는 빈 아이디로 설정
        return "user/findId";
    }

    // 아이디 찾기
    @PostMapping("/findId")
    public ResponseEntity<String> findId(@RequestBody UserVO userVO) {
        try {
            String result = us.findId(userVO);
            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


}

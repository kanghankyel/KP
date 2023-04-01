package com.kidsplace.kidsplace.security;

import com.kidsplace.kidsplace.commons.AuthVO;
import com.kidsplace.kidsplace.commons.UserVO;
import com.kidsplace.kidsplace.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.kidsplace.kidsplace.security.CustomMember;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO vo = null;
        try{
            // 아이디로 검색된 사용자가 존재하고 있는지부터 검색
            System.out.println(username);
            vo = userDAO.getMemberByID(username);           // username은 시큐리티에서 날아오는 아이디
            if (vo != null){
                logger.info(username + " -> 존재하는 유저입니다.");
            } else {
                logger.error(username + " -> 존재하지 않는 유저입니다.");
            }
        } catch (Exception e){
            logger.error("회원가입 데이터베이스 연결 오류");
            e.printStackTrace();
        }
        return vo == null ? null : new CustomMember(vo);
    }

    // 유저가 있을 시 User를 상속받은 createUser로 토스, 그렇지 않을 경우 null
    // createUser는 vo를 받은 후 비밀번호까지 비교하고, 인증정보와 함께 session영역에 저장시켜준다.
    // private UserDetails createUser(UserVO vo) {
    //     return new org.springframework.security.core.userdetails.User(
    //             vo.getuId(),
    //             vo.getuPassword(),
    //             authorities(vo.getuAuthList())
    //     );
    // }

    // private Collection<? extends GrantedAuthority> authorities(List<AuthVO> getuAuthList) {
    //     List<GrantedAuthority> gList = new ArrayList<>();
    //     for (AuthVO authVO : getuAuthList){
    //         gList.add(new SimpleGrantedAuthority(authVO.getuAuth()));
    //     }
    //     return gList;
    // }

}

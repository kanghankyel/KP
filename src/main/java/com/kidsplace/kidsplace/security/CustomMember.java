package com.kidsplace.kidsplace.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.kidsplace.kidsplace.commons.AuthVO;
import com.kidsplace.kidsplace.commons.UserVO;

import lombok.Getter;

public class CustomMember extends User {
    
    @Getter
    private UserVO member;

    //단순히 오류를 없애기 위한 전체생성자
    //아이디, 비밀번호, 권한을 비교한다.
    public CustomMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    //아까 CustomUserDetailService에서 id만을 통해 vo를 가져왔으니,
    //이제 vo를 통해 아이디와 비밀번호를 비교한다.
    public CustomMember(UserVO vo) {
        //아이디, 비밀번호, 인가정보
        super(vo.getuId(), vo.getuPassword(), authorities(vo.getuAuthList()));

        //전역변수 member 갱신하기
        this.member = vo;
        //세션 객체에 활용할 수 있는 vo가 없기 때문에(security가 인가를 해준 것이기 때문에) jsp에서 활용할 수 있도록 직접 넣어준다.
    }

    public static Collection<? extends GrantedAuthority> authorities(List<AuthVO> authList) {
        List<GrantedAuthority> gList = new ArrayList<>();
        for (AuthVO vo : authList) {
            gList.add(new SimpleGrantedAuthority(vo.getuAuth()));
        }
        return gList;
    }
    
}

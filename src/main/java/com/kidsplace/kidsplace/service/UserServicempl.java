package com.kidsplace.kidsplace.service;

import com.kidsplace.kidsplace.commons.AuthVO;
import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.UserVO;
import com.kidsplace.kidsplace.dao.UserDAO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServicempl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 아이디 중복 검사
    @Override
    public int uIdCheck(String uId) {
        return userDAO.uIdCheck(uId);
    }

    @Override
    public boolean getMemberByPhoneNum(String uPhoneNum) throws Exception {
        return false;
    }

    // 회원가입
    @Override
    @Transactional
    public void signUp(UserVO vo) throws Exception {
        if (userDAO.getMemberByID(vo.getuId()) != null){
            throw new RuntimeException("이미 가입되어 있는 아이디입니다.");
        }
        String pw = vo.getuPassword();
        String encodePw = passwordEncoder.encode(pw);
        vo.setuPassword(encodePw);
        userDAO.signUp(vo);
        AuthVO authVO = new AuthVO(vo.getuId(), "ROLE_USER");
        userDAO.insertAuth(authVO);
    }

    @Override
    public String findId(UserVO vo) throws Exception {
        return null;
    }

    @Override
    public UserVO findPass(UserVO vo) throws Exception {
        return null;
    }

    @Override
    public void changePass(UserVO vo) throws Exception {

    }

    @Override
    public void updateVisit(UserVO vo) throws Exception {

    }

    @Override
    public void update(UserVO vo) throws Exception {

    }

    @Override
    public void withdraw(UserVO vo) throws Exception {

    }

    // @Override
    // public List<UserVO> userList() {
    //     return userDAO.userList();
    // }

    @Override
    public List<UserVO> userList(Pagination pagination) {
        logger.info("get List with SearchDTO");
        return userDAO.userList(pagination);
    }

    @Override
    public int userCount() {
        return userDAO.userCount();
    }

}

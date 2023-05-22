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

    // @Override
    // public String findId(UserVO vo) throws Exception {
    //     return null;
    // }

    // @Override
    // public UserVO findPass(UserVO vo) throws Exception {
    //     return null;
    // }

    // @Override
    // public void changePass(UserVO vo) throws Exception {

    // }

    
    @Override
    public boolean updateVisit(UserVO vo) {
        try{
            int result = userDAO.updateVisit(vo);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("방문일 업데이트 오류");
            e.printStackTrace();
            return false;
        }
    }

    // @Override
    // public void update(UserVO vo) throws Exception {

    // }

    // @Override
    // public void withdraw(UserVO vo) throws Exception {

    // }

    // @Override
    // public List<UserVO> userList() {
    //     return userDAO.userList();
    // }

    @Override
    public List<UserVO> userList(Pagination pagination, UserVO userVO, AuthVO authVO) {
        logger.info("get List with SearchDTO");
        return userDAO.userList(pagination, userVO, authVO);
    }

    @Override
    public int userCount(UserVO userVO, AuthVO authVO) {
        return userDAO.userCount(userVO, authVO);
    }

    @Override
    public boolean userAuthUp(UserVO userVO) {
        // logger.warn("회원등급업 처리 데이터");
        // logger.warn(String.valueOf(userVO));
        try{
            int result = userDAO.userAuthUp(userVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("회원등급업 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean userAuthDown(UserVO userVO) {
        // logger.warn("회원등급다운 처리 데이터");
        // logger.warn(String.valueOf(userVO));
        try{
            int result = userDAO.userAuthDown(userVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("회원등급다운 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean userBlock(UserVO userVO) {
        // logger.warn("회원블락 처리 데이터");
        // logger.warn(String.valueOf(userVO));
        try{
            int result = userDAO.userBlock(userVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("회원블락처리 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean userNotBlock(UserVO userVO) {
        // logger.warn("회원블락해제 처리 데이터");
        // logger.warn(String.valueOf(userVO));
        try{
            int result = userDAO.userNotBlock(userVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("회원블락해제처리 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UserVO> userInfo(String uNum) {
        return userDAO.userInfo(uNum);
    }

    @Override
    public boolean userEdit(UserVO userVO) {
        logger.warn("회원정보 수정 데이터");
        logger.warn(String.valueOf(userVO));
        try{
            int result = userDAO.userEdit(userVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("회원정보 수정 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean userDelete(UserVO userVO) {
        logger.warn("회원정보 탈퇴 데이터");
        logger.warn(String.valueOf(userVO));
        try{
            int result = userDAO.userDelete(userVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("회원탈퇴 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean PasswordEdit(UserVO userVO) {
        logger.warn("비밀번호 변경 데이터");
        logger.warn(String.valueOf(userVO));
        try{
            // 비밀번호 인코딩
            String pw = userVO.getuPassword();
            String encodePw = passwordEncoder.encode(pw);
            userVO.setuPassword(encodePw);

            // 비밀번호 변경
            int result = userDAO.passwordEdit(userVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("비밀번호 변경 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String findId(UserVO userVO) {
        try {
            return userDAO.findId(userVO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

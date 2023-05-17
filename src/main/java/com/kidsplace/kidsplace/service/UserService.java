package com.kidsplace.kidsplace.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kidsplace.kidsplace.commons.AuthVO;
import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.UserVO;

public interface UserService {

    // 아이디 중복 체크
    int uIdCheck(String uId);

    //휴대폰 중복 체크
    boolean getMemberByPhoneNum(String uPhoneNum) throws Exception;

    //회원가입
    void signUp(UserVO vo) throws Exception;

    //로그인
    //void signIn(String uId) throws Exception;
    //시큐리로 작동

    //아이디 찾기
    // String findId(UserVO vo) throws Exception;

    //비밀번호 찾기 - 정확히는 바꾸기 페이지
    // UserVO findPass(UserVO vo) throws Exception;

    //비밀번호 변경
    // void changePass(UserVO vo) throws Exception;

    //방문기록 갱신
    boolean updateVisit(UserVO vo) throws Exception;

    //회원정보 수정
    // void update(UserVO vo) throws Exception;

    //회원탈퇴
    // void withdraw(UserVO vo) throws Exception;

    // 회원 리스트 페이징
    List<UserVO> userList(Pagination pagination);

    // 회원 리스트 카운팅
    int userCount();

    // 회원등급업
    boolean userAuthUp(UserVO userVO);

    // 회원등급다운
    boolean userAuthDown(UserVO userVO);

    // 회원블락
    boolean userBlock(UserVO userVO);

    // 회원블락해제
    boolean userNotBlock(UserVO userVO);

    // 마이페이지 회원정보
    List<UserVO> userInfo(@Param("uNum") int uNum);

    // 회원정보 수정 데이터베이스 반영
    boolean userEdit(UserVO userVO);

    // 회원탈퇴
    boolean userDelete(UserVO userVO);

}

package com.kidsplace.kidsplace.dao;

import com.kidsplace.kidsplace.commons.AuthVO;
import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.UserVO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDAO {

    //로그인 시 인가정보 들고오기(전체를 들고와서 암호화된 키 비교)
    UserVO getMemberByID(@Param("uId") String uId);

    //휴대폰 중복 체크
    UserVO getMemberByPhoneNum(String uPhoneNum) throws Exception;

    //회원가입
    void signUp(UserVO vo) throws Exception;

    //가입된 회원에게 기본권한 부여
    void insertAuth(AuthVO vo) throws Exception;

    //아이디 중복 검사
    int uIdCheck(String uId);

    //방문기록 갱신
    int updateVisit(UserVO vo) throws Exception;

    // 회원 리스트 페이징
    List<UserVO> userList(@Param("pagination") Pagination pagination, @Param("userVO") UserVO userVO, @Param("authVO") AuthVO authVO) throws Exception;

    // 회원 리스트 카운팅
    int userCount(@Param("userVO") UserVO userVO, @Param("authVO") AuthVO authVO);

    // 회원등급업
    int userAuthUp(UserVO userVO) throws Exception;

    // 회원등급다운
    int userAuthDown(UserVO userVO) throws Exception;

    // 회원블락
    int userBlock(UserVO userVO) throws Exception;

    // 회원블락해제
    int userNotBlock(UserVO userVO) throws Exception;

    // 마이페이지 회원정보
    List<UserVO> userInfo(String uNum) throws Exception;

    // 회원정보 수정 데이터베이스 반영
    int userEdit(UserVO userVO) throws Exception;

    // 회원탈퇴
    int userDelete(UserVO userVO) throws Exception;

    // 비밀번호 변경
    int passwordEdit(UserVO userVO) throws Exception;

    // 아이디 찾기
    String findId(UserVO userVO) throws Exception;


}

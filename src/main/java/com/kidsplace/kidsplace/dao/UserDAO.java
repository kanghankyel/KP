package com.kidsplace.kidsplace.dao;

import com.kidsplace.kidsplace.commons.AuthVO;
import com.kidsplace.kidsplace.commons.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
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

    //회원 아이디 찾기
    String findId(UserVO vo) throws Exception;

    //회원 비밀번호 찾기 - 정확히는 변경 페이지로
    UserVO findPass(UserVO vo) throws Exception;

    //비밀번호 변경
    void changePass(UserVO vo) throws Exception;

    //회원정보 수정
    void update(UserVO vo) throws Exception;

    //방문기록 갱신
    void updateVisit(UserVO vo) throws Exception;

    //회원탈퇴
    @Update("UPDATE kidslandUser SET member = 'N' WHERE uId = #{uId}")
    void withdraw(UserVO vo) throws Exception;

}

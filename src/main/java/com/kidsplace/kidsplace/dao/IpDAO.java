package com.kidsplace.kidsplace.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IpDAO {

    // 접속한 ip정보 넣기
    void insertIp(String clientIp);

    // 방문자 집계 카운트
    int adminVisitSum();

}

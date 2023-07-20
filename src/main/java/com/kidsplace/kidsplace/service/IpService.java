package com.kidsplace.kidsplace.service;

import java.util.List;

import com.kidsplace.kidsplace.commons.IpVO;

public interface IpService {

    // 접속한 ip정보 넣기
    void insertIp(String clientIp);

    // 방문자 집계 카운트
    int adminVisitSum();

}

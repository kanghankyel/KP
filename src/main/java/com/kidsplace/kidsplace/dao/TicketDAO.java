package com.kidsplace.kidsplace.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kidsplace.kidsplace.commons.TicketDetailVO;
import com.kidsplace.kidsplace.commons.TicketVO;

@Mapper
public interface TicketDAO {
    
    // 티켓정보 리스트 불러오기
    List<TicketDetailVO> ticketDetailList();

    // 티켓구입 정보 저장
    int ticketInsert(TicketVO ticketVO);

    // 티켓구매내역(개인) 리스트 구현
    List<TicketVO> ticketList();

}

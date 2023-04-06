package com.kidsplace.kidsplace.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kidsplace.kidsplace.commons.TicketDetailVO;
import com.kidsplace.kidsplace.commons.TicketVO;

public interface TicketService {
    
    // 티켓정보 리스트 불러오기
    List<TicketDetailVO> ticketDetailList();

    // 티켓구입 정보 입력
    Boolean ticketInsert(TicketVO ticketVO);

    // 티켓구매내역(개인) 리스트 구현
    List<TicketVO> ticketList();

}

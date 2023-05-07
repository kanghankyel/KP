package com.kidsplace.kidsplace.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kidsplace.kidsplace.commons.Criteria;
import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.TicketDetailVO;
import com.kidsplace.kidsplace.commons.TicketVO;

public interface TicketService {
    
    // 티켓정보 리스트 불러오기
    List<TicketDetailVO> ticketDetailList();

    // 티켓구입 정보 입력
    Boolean ticketInsert(TicketVO ticketVO);

    // 티켓구매내역(개인) 리스트 구현
    List<TicketVO> ticketList(@Param("uNum") int uNum);

    // 티켓구매내역(개인) 리스트 페이징
    List<TicketVO> ticketPaging(@Param("pagination") Pagination pagination, @Param("uNum") int uNum);

    // 티켓구매내역(개인) 리스트 카운팅
    int ticketCount(int uNum);

    // 티켓구매내역(전체) 리스트 페이징
    List<TicketVO> ticketAllPaging(Pagination pagination);

    // 티켓구매내역(전체) 리스트 카운팅
    int ticketAllCount();

    // 티켓환불요청
    boolean ticketRefund(TicketVO ticketVO);

    // 티켓사용처리
    boolean ticketUseCheck(TicketVO ticketVO);

    // 티켓환불처리
    boolean ticketRefundCheck(TicketVO ticketVO);

}

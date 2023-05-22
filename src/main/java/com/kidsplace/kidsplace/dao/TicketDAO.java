package com.kidsplace.kidsplace.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.TicketDetailVO;
import com.kidsplace.kidsplace.commons.TicketVO;
import com.kidsplace.kidsplace.commons.UserVO;

@Mapper
public interface TicketDAO {
    
    // 티켓정보 리스트 불러오기
    List<TicketDetailVO> ticketDetailList();

    // 티켓구입 정보 저장
    int ticketInsert(TicketVO ticketVO);

    // 티켓구매내역(개인) 리스트 구현
    List<TicketVO> ticketList(int uNum);

    // 티켓구매내역(개인) 리스트 페이징
    List<TicketVO> ticketPaging(@Param("pagination") Pagination pagination, @Param("uNum") String uNum);

    // 티켓구매내역(개인) 리스트 카운팅
    int ticketCount(@Param("uNum") String uNum);

    // 티켓구매내역(전체) 리스트 페이징
    List<TicketVO> ticketAllPaging(@Param("pagination") Pagination pagination, @Param("ticketVO") TicketVO ticketVO, @Param("userVO") UserVO userVO);

    // 티켓구매내역(전체) 리스트 카운팅
    int ticketAllCount(@Param("ticketVO") TicketVO ticketVO, @Param("userVO") UserVO userVO);

    // 티켓환불요청
    int ticketRefund(TicketVO ticketVO);

    // 티켓사용처리
    int ticketUseCheck(TicketVO ticketVO);

    // 티켓환불처리
    int ticketRefundCheck(TicketVO ticketVO);

}

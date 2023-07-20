package com.kidsplace.kidsplace.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.TicketDetailVO;
import com.kidsplace.kidsplace.commons.TicketVO;
import com.kidsplace.kidsplace.commons.UserVO;

public interface TicketService {
    
    // 티켓정보 리스트 불러오기
    List<TicketDetailVO> ticketDetailList();

    // 티켓구입 정보 입력
    Boolean ticketInsert(TicketVO ticketVO);

    // 티켓구매내역(개인) 리스트 구현
    List<TicketVO> ticketList(@Param("uNum") int uNum);

    // 티켓구매내역(개인) 리스트 페이징
    List<TicketVO> ticketPaging(@Param("pagination") Pagination pagination, @Param("uNum") String uNum);

    // 티켓구매내역(개인) 리스트 카운팅
    int ticketCount(String uNum);

    // 티켓구매내역(전체) 리스트 페이징
    List<TicketVO> ticketAllPaging(Pagination pagination, TicketVO ticketVO, UserVO userVO);

    // 티켓구매내역(전체) 리스트 카운팅
    int ticketAllCount(TicketVO ticketVO, UserVO userVO);

    // 티켓환불요청
    boolean ticketRefund(TicketVO ticketVO);

    // 티켓사용처리
    boolean ticketUseCheck(TicketVO ticketVO);

    // 티켓환불처리
    boolean ticketRefundCheck(TicketVO ticketVO);

    // 티켓정보 삭제처리
    boolean ticketInfoDelete(TicketDetailVO ticketDetailVO);

    // 티켓정보 추가
    boolean ticketInfoCreate(TicketDetailVO ticketDetailVO);

    // 티켓정보 수정
    boolean ticketInfoEdit(TicketDetailVO ticketDetailVO);

    // 티켓정보 리스트
    List<TicketVO> ticketDateList(TicketVO ticketVO);

    // 일별 구매 정산
    List<TicketVO> ticketDayBuySum(TicketVO ticketVO);

    // 일별 환불 정산
    List<TicketVO> ticketDayRefundSum(TicketVO ticketVO);

    // 일별 수익 총계
    List<TicketVO> ticketDayTotal(TicketVO ticketVO);

    // 월별 구매 정산
    List<TicketVO> ticketMonthList(TicketVO ticketVO);

    // 월별 환불 정산
    List<TicketVO> ticketMonthRefund(TicketVO ticketVO);

    // 월별 수익 총계
    List<TicketVO> ticketMonthTotal(TicketVO ticketVO);

    // 기간 티켓정보 리스트
    List<TicketVO> ticketSinceList(TicketVO ticketVO);

    // 기간별 구매 정산
    List<TicketVO> ticketSinceBuySum(TicketVO ticketVO);

    // 기간별 환불 정산
    List<TicketVO> ticketSinceRefundSum(TicketVO ticketVO);

    // 기간별 수익 총계
    List<TicketVO> ticketSinceTotal(TicketVO ticketVO);

    // 관리자 메인페이지 오늘 총계액
    List<TicketVO> adminBuySum();

    // 관리자 메인페이지 환불건수 데이터
    List<TicketVO> adminRefundSum();

}

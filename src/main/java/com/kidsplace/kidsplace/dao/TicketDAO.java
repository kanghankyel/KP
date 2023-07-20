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
    List<TicketDetailVO> ticketDetailList() throws Exception;

    // 티켓구입 정보 저장
    int ticketInsert(TicketVO ticketVO) throws Exception;

    // 티켓구매내역(개인) 리스트 구현
    List<TicketVO> ticketList(int uNum) throws Exception;

    // 티켓구매내역(개인) 리스트 페이징
    List<TicketVO> ticketPaging(@Param("pagination") Pagination pagination, @Param("uNum") String uNum) throws Exception;

    // 티켓구매내역(개인) 리스트 카운팅
    int ticketCount(@Param("uNum") String uNum);

    // 티켓구매내역(전체) 리스트 페이징
    List<TicketVO> ticketAllPaging(@Param("pagination") Pagination pagination, @Param("ticketVO") TicketVO ticketVO, @Param("userVO") UserVO userVO) throws Exception;

    // 티켓구매내역(전체) 리스트 카운팅
    int ticketAllCount(@Param("ticketVO") TicketVO ticketVO, @Param("userVO") UserVO userVO);

    // 티켓환불요청
    int ticketRefund(TicketVO ticketVO) throws Exception;

    // 티켓사용처리
    int ticketUseCheck(TicketVO ticketVO) throws Exception;

    // 티켓환불처리
    int ticketRefundCheck(TicketVO ticketVO) throws Exception;

    // 티켓정보 삭제처리
    int ticketInfoDelete(TicketDetailVO ticketDetailVO) throws Exception;

    // 티켓정보 추가
    int ticketInfoCreate(TicketDetailVO ticketDetailVO) throws Exception;

    // 티켓정보 수정
    int ticketInfoEdit(TicketDetailVO ticketDetailVO) throws Exception;

    // 티켓정보 리스트
    List<TicketVO> ticketDateList(@Param("ticketVO") TicketVO ticketVO) throws Exception;

    // 일별 구매 정산
    List<TicketVO> ticketDayBuySum(@Param("ticketVO") TicketVO ticketVO) throws Exception;

    // 일별 환불 정산
    List<TicketVO> ticketDayRefundSum(@Param("ticketVO") TicketVO ticketVO) throws Exception;

    // 일별 수익 총계
    List<TicketVO> ticketDayTotal(@Param("ticketVO") TicketVO ticketVO) throws Exception;

    // 월별 구매 정산
    List<TicketVO> ticketMonthList(@Param("ticketVO") TicketVO ticketVO) throws Exception;

    // 월별 환불 정산
    List<TicketVO> ticketMonthRefund(@Param("ticketVO") TicketVO ticketVO) throws Exception;

    // 월별 수익 총계
    List<TicketVO> ticketMonthTotal(@Param("ticketVO") TicketVO ticketVO) throws Exception;

    // 기간 티켓정보 리스트
    List<TicketVO> ticketSinceList(@Param("ticketVO") TicketVO ticketVO) throws Exception;

    // 기간별 구매 정산
    List<TicketVO> ticketSinceBuySum(@Param("ticketVO") TicketVO ticketVO) throws Exception;

    // 기간별 환불 정산
    List<TicketVO> ticketSinceRefundSum(@Param("ticketVO") TicketVO ticketVO) throws Exception;

    // 기간별 수익 총계
    List<TicketVO> ticketSinceTotal(@Param("ticketVO") TicketVO ticketVO) throws Exception;

    // 관리자 메인페이지 오늘 총계액
    List<TicketVO> adminBuySum() throws Exception;

    // 관리자 메인페이지 환불건수 데이터
    List<TicketVO> adminRefundSum() throws Exception;
    
}

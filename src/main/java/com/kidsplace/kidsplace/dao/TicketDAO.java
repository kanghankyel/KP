package com.kidsplace.kidsplace.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kidsplace.kidsplace.commons.Criteria;
import com.kidsplace.kidsplace.commons.TicketDetailVO;
import com.kidsplace.kidsplace.commons.TicketVO;

@Mapper
public interface TicketDAO {
    
    // 티켓정보 리스트 불러오기
    List<TicketDetailVO> ticketDetailList();

    // 티켓구입 정보 저장
    int ticketInsert(TicketVO ticketVO);

    // 티켓구매내역(개인) 리스트 구현
    List<TicketVO> ticketList(int uNum);



    // mapper패키지인 BoardDAO인터페이스에 Criteria클래스를 파라미터로 사용하는 메소드를 추가
    // 페이징 처리 후 리스트 조회
    List<TicketVO> getTicketListWithPaging(Criteria cri);

    // 페이징 total 카운트
    int getTotalCount(Criteria cri);

}

package com.kidsplace.kidsplace.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.TicketDetailVO;
import com.kidsplace.kidsplace.commons.TicketVO;
import com.kidsplace.kidsplace.dao.TicketDAO;

@Service
public class TicketServicempl implements TicketService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TicketDAO ticketDAO;

    @Override
    public List<TicketDetailVO> ticketDetailList() {
        return ticketDAO.ticketDetailList();
    }

    @Override
    @Transactional
    public Boolean ticketInsert(TicketVO ticketVO) {
        // logger.warn("티켓구매 데이터 입력");
        // logger.warn(String.valueOf(ticketVO));
        try{
            int result = ticketDAO.ticketInsert(ticketVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("티켓구매 데이터 입력 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TicketVO> ticketList(int uNum) {
        return ticketDAO.ticketList(uNum);
    }

    @Override
    public List<TicketVO> ticketPaging(Pagination pagination, int uNum) {
        logger.info("get List with SearchDTO");
        return ticketDAO.ticketPaging(pagination, uNum);
    }

    @Override
    public int ticketCount(int uNum) {
        int result = ticketDAO.ticketCount(uNum);
        System.out.println("ticketCount : " + result);
        return result;
    }

    @Override
    public List<TicketVO> ticketAllPaging(Pagination pagination, TicketVO ticketVO) {
        logger.info("get List with SearchDTO");
        return ticketDAO.ticketAllPaging(pagination, ticketVO);
    }

    @Override
    public int ticketAllCount(TicketVO ticketVO) {
        return ticketDAO.ticketAllCount(ticketVO);
    }

    @Override
    public boolean ticketRefund(TicketVO ticketVO) {
        // logger.warn("티켓환불요청 데이터");
        // logger.warn(String.valueOf(ticketVO));
        try{
            int result = ticketDAO.ticketRefund(ticketVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("티켓환불요청 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ticketUseCheck(TicketVO ticketVO) {
        // logger.warn("티켓사용처리 데이터");
        // logger.warn(String.valueOf(ticketVO));
        try{
            int result = ticketDAO.ticketUseCheck(ticketVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("티켓사용처리 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ticketRefundCheck(TicketVO ticketVO) {
        // logger.warn("티켓환불처리 데이터");
        // logger.warn(String.valueOf(ticketVO));
        try{
            int result = ticketDAO.ticketRefundCheck(ticketVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("티켓환불처리 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

}

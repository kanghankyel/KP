package com.kidsplace.kidsplace.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.TicketDetailVO;
import com.kidsplace.kidsplace.commons.TicketVO;
import com.kidsplace.kidsplace.commons.UserVO;
import com.kidsplace.kidsplace.dao.TicketDAO;

@Service
public class TicketServicempl implements TicketService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TicketDAO ticketDAO;

    @Override
    public List<TicketDetailVO> ticketDetailList() {
        // return ticketDAO.ticketDetailList();
        try {
            return ticketDAO.ticketDetailList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
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
        // return ticketDAO.ticketList(uNum);
        try {
            return ticketDAO.ticketList(uNum);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<TicketVO> ticketPaging(Pagination pagination, String uNum) {
        // logger.info("get List with SearchDTO");
        // return ticketDAO.ticketPaging(pagination, uNum);
        try {
            return ticketDAO.ticketPaging(pagination, uNum);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public int ticketCount(String uNum) {
        int result = ticketDAO.ticketCount(uNum);
        // System.out.println("ticketCount : " + result);
        return result;
    }

    @Override
    public List<TicketVO> ticketAllPaging(Pagination pagination, TicketVO ticketVO, UserVO userVO) {
        // logger.info("get List with SearchDTO");
        // return ticketDAO.ticketAllPaging(pagination, ticketVO, userVO);
        try {
            return ticketDAO.ticketAllPaging(pagination, ticketVO, userVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public int ticketAllCount(TicketVO ticketVO, UserVO userVO) {
        return ticketDAO.ticketAllCount(ticketVO, userVO);
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

    @Override
    public boolean ticketInfoDelete(TicketDetailVO ticketDetailVO) {
        // logger.warn("티켓정보 삭제처리 데이터");
        // logger.warn(String.valueOf(ticketDetailVO));
        try{
            int result = ticketDAO.ticketInfoDelete(ticketDetailVO);
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
    public boolean ticketInfoCreate(TicketDetailVO ticketDetailVO) {
        // logger.warn("티켓정보 추가처리 데이터");
        // logger.warn(String.valueOf(ticketDetailVO));
        try{
            int result = ticketDAO.ticketInfoCreate(ticketDetailVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("티켓정보 추가처리 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ticketInfoEdit(TicketDetailVO ticketDetailVO) {
        // logger.warn("티켓정보 수정처리 데이터");
        // logger.warn(String.valueOf(ticketDetailVO));
        try{
            int result = ticketDAO.ticketInfoEdit(ticketDetailVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("티켓정보 수정처리 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TicketVO> ticketDateList(TicketVO ticketVO) {
        try {
            return ticketDAO.ticketDateList(ticketVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<TicketVO> ticketDayBuySum(TicketVO ticketVO) {
        // return ticketDAO.ticketDayBuySum(ticketVO);
        try {
            return ticketDAO.ticketDayBuySum(ticketVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<TicketVO> ticketDayRefundSum(TicketVO ticketVO) {
        // return ticketDAO.ticketDayRefundSum(ticketVO);
        try {
            return ticketDAO.ticketDayRefundSum(ticketVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<TicketVO> ticketDayTotal(TicketVO ticketVO) {
        // return ticketDAO.ticketDayTotal(ticketVO);
        try {
            return ticketDAO.ticketDayTotal(ticketVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<TicketVO> ticketMonthList(TicketVO ticketVO) {
        // return ticketDAO.ticketMonthList(ticketVO);
        try {
            return ticketDAO.ticketMonthList(ticketVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<TicketVO> ticketMonthRefund(TicketVO ticketVO) {
        // return ticketDAO.ticketMonthRefund(ticketVO);
        try {
            return ticketDAO.ticketMonthRefund(ticketVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<TicketVO> ticketMonthTotal(TicketVO ticketVO) {
        // return ticketDAO.ticketMonthTotal(ticketVO);
        try {
            return ticketDAO.ticketMonthTotal(ticketVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<TicketVO> ticketSinceList(TicketVO ticketVO) {
        // return ticketDAO.ticketSinceList(ticketVO);
        try {
            return ticketDAO.ticketSinceList(ticketVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<TicketVO> ticketSinceBuySum(TicketVO ticketVO) {
        // return ticketDAO.ticketSinceBuySum(ticketVO);
        try {
            return ticketDAO.ticketSinceBuySum(ticketVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<TicketVO> ticketSinceRefundSum(TicketVO ticketVO) {
        // return ticketDAO.ticketSinceRefundSum(ticketVO);
        try {
            return ticketDAO.ticketSinceRefundSum(ticketVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<TicketVO> ticketSinceTotal(TicketVO ticketVO) {
        // return ticketDAO.ticketSinceTotal(ticketVO);
        try {
            return ticketDAO.ticketSinceTotal(ticketVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<TicketVO> adminBuySum() {
        // return ticketDAO.adminBuySum();
        try {
            return ticketDAO.adminBuySum();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<TicketVO> adminRefundSum() {
        // return ticketDAO.adminRefundSum();
        try {
            return ticketDAO.adminRefundSum();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}

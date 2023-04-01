package com.kidsplace.kidsplace.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        logger.warn("티켓구매 데이터 입력");
        logger.warn(String.valueOf(ticketVO));
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

}

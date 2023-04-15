package com.kidsplace.kidsplace.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kidsplace.kidsplace.commons.TicketDetailVO;
import com.kidsplace.kidsplace.commons.TicketVO;
import com.kidsplace.kidsplace.service.TicketService;

@Controller
@RequestMapping("ticket")
public class TicketController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TicketService ticketService;

    // 티켓예매 페이지 이동
    // @GetMapping("/ticketBuy")
    // public String TicketPage(){
    //     return "/ticket/ticketBuy";
    // }
    
    // 티켓예매 페이지 이동 + 티켓정보
    @GetMapping("/ticketBuy")
    public String TicketInfo(Model model){
        List<TicketDetailVO> ticketDetailList = ticketService.ticketDetailList();
        model.addAttribute("ticketDetail", ticketDetailList);
        return "/ticket/ticketBuy";
    }

    // 티켓구입 정보 저장
    @PostMapping("/ticketBuy")
    public ResponseEntity<Boolean> TicketBuy(@RequestBody TicketVO ticketVO){
        try{
            ticketService.ticketInsert(ticketVO);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception e){
            logger.error("티켓예매 정보 저장에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 티켓구매내역(개인) 리스트 구현
    @GetMapping("/ticketHistory")
    public String TicketList (Model model){
        List<TicketVO> ticketlist = ticketService.ticketList();
        model.addAttribute("ticket", ticketlist);
        return "/ticket/ticketHistory";
    }

}

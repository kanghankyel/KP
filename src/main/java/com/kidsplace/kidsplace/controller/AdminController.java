package com.kidsplace.kidsplace.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kidsplace.kidsplace.commons.TicketDetailVO;
import com.kidsplace.kidsplace.commons.TicketVO;
import com.kidsplace.kidsplace.service.TicketService;

@Controller
@RequestMapping("admin")
public class AdminController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TicketService ticketService;

    // 관리자페이지 메인 이동
    @GetMapping("/admin")
    public String AdminPage(){
        return "/admin/admin";
    }

    // 관리자페이지 티켓페이지 이동
    @GetMapping("/adminTicket")
    public String TicketInfo(Model model){
        // 티켓정보
        List<TicketDetailVO> ticketDetailList = ticketService.ticketDetailList();
        model.addAttribute("ticketDetail", ticketDetailList);
        // 티켓구매리스트
        List<TicketVO> ticketlist = ticketService.ticketList();
        model.addAttribute("ticket", ticketlist);
        return "/admin/adminTicket";
    }
    
}

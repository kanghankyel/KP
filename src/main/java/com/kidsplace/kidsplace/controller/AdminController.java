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
import com.kidsplace.kidsplace.service.TicketService;

@Controller
@RequestMapping("admin")
public class AdminController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TicketService ticketService;

    // 티켓정보 이동
    @GetMapping("/admin")
    public String TicketInfo(Model model){
        List<TicketDetailVO> ticketDetailList = ticketService.ticketDetailList();
        model.addAttribute("ticketDetail", ticketDetailList);
        return "/admin/admin";
    }
}

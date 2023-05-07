package com.kidsplace.kidsplace.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.kidsplace.kidsplace.commons.Criteria;
import com.kidsplace.kidsplace.commons.PageDTO;
import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.TicketDetailVO;
import com.kidsplace.kidsplace.commons.TicketVO;
import com.kidsplace.kidsplace.service.TicketService;
import com.kidsplace.kidsplace.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    // 관리자페이지 메인 이동
    @GetMapping("/admin")
    public String AdminPage(){
        return "/admin/admin";
    }

    // 관리자페이지 티켓페이지 이동
    @GetMapping("/adminTicket")
    public String TicketInfo(Model model
                            , @RequestParam(name = "page", required = false, defaultValue = "1") int page){
        // 티켓정보
        List<TicketDetailVO> ticketDetailList = ticketService.ticketDetailList();
        model.addAttribute("ticketDetail", ticketDetailList);
        // 티켓구매리스트
        // List<TicketVO> ticketlist = ticketService.ticketList();
        // model.addAttribute("ticket", ticketlist);
        // 티켓구매리스트 페이징
        int ticketAllCount = ticketService.ticketAllCount();
        Pagination pagination = new Pagination(ticketAllCount, page);
        List<TicketVO> ticketalllist = ticketService.ticketAllPaging(pagination);
        model.addAttribute("ticket", ticketalllist);
        model.addAttribute("page", pagination);
        System.out.println(pagination.toString());
        return "admin/adminTicket";
    }

    // 티켓사용처리
    @PostMapping("/useComplete")
    public ResponseEntity<Boolean> ticketUseCheck(@RequestBody TicketVO ticketVO){
        try{
            boolean result = ticketService.ticketUseCheck(ticketVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("티켓사용 요청에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 티켓환불처리
    @PostMapping("/refundComplete")
    public ResponseEntity<Boolean> ticketRefundCheck(@RequestBody TicketVO ticketVO){
        try{
            boolean result = ticketService.ticketRefundCheck(ticketVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("티켓환불 요청에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // @GetMapping("/adminTicket")
    // public void TicketList(Criteria cri, Model model){
    //     logger.info("티켓리스트 : " + cri);
    //     model.addAttribute("ticketList", ticketService.getTicketList(cri));
    // } 

    // // 관리자페이지 회원리스트 페이지 이동 및 회원리스트 구현
    // @GetMapping("/adminUser")
    // public String UserList(Model model){
    //     List<UserVO> userlist = userService.userList();
    //     model.addAttribute("user", userlist);
    //     return "/admin/adminUser";
    // }
    
    
}

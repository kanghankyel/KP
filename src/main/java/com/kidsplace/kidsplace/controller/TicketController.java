package com.kidsplace.kidsplace.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.TicketDetailVO;
import com.kidsplace.kidsplace.commons.TicketVO;
import com.kidsplace.kidsplace.security.CustomMember;
import com.kidsplace.kidsplace.service.TicketService;

@Controller
@RequestMapping("ticket")
public class TicketController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TicketService ticketService;
    
    // 티켓예매 페이지 이동 + 티켓정보
    @GetMapping("/ticketBuy")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String TicketInfo(Model model){
        List<TicketDetailVO> ticketDetailList = ticketService.ticketDetailList();
        model.addAttribute("ticketDetail", ticketDetailList);
        return "ticket/ticketBuy";
    }

    // 티켓구입 정보 저장
    // connection 수에 따라 반복문을 sql단 또는 java단 선택 사용.(sql insert는 한번에 하나의 정보 주입. select는 예외)
    @PostMapping("/ticketBuy")
    public ResponseEntity<Boolean> TicketBuy(@RequestBody List<TicketVO> ticketVO){
        System.out.println(ticketVO);
        try{
            // 반복자(iterator 또는 향상된 for문을 이용해 정보를 개별로 전달.
            for(TicketVO item : ticketVO) {
                for (int i=0; i<item.gettCount();i++) {
                    ticketService.ticketInsert(item);
                }
            }
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception e){
            logger.error("티켓예매 정보 저장에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 티켓구매내역(개인) 리스트 구현
    @GetMapping("/ticketHistory")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String TicketList (Model model
                            , @AuthenticationPrincipal CustomMember customMember
                            , @RequestParam(name = "page", required = false, defaultValue = "1") int page){
        // int uNum = customMember.getMember().getuNum();
        // // System.out.println(customMember.getMember().getuNum());
        // List<TicketVO> ticketlist = ticketService.ticketList(uNum);
        // model.addAttribute("ticket", ticketlist);
        // return "/ticket/ticketHistory";

        String uNum = customMember.getMember().getuNum();
        // ###################### ticketDAO단에서 ticketCount값을 0으로 받아옴
        int ticketCount = ticketService.ticketCount(uNum);
        // System.out.println("ticketCount : " + ticketCount);
        Pagination pagination = new Pagination(ticketCount, page);
        System.out.println("uNum : " + customMember.getMember().getuNum());
        List<TicketVO> ticketlist = ticketService.ticketPaging(pagination, uNum);
        model.addAttribute("ticket", ticketlist);
        model.addAttribute("page", pagination);
        System.out.println(pagination.toString());
        return "ticket/ticketHistory";
    }

    // 티켓환불요청
    @PostMapping("/ticketHistory")
    public ResponseEntity<Boolean> ticketRefund(@RequestBody TicketVO ticketVO){
        try{
            boolean result = ticketService.ticketRefund(ticketVO);
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

}

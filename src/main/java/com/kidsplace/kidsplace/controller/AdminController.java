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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.TicketDetailVO;
import com.kidsplace.kidsplace.commons.TicketVO;
import com.kidsplace.kidsplace.commons.UserVO;
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
                            , @RequestParam(name = "page", required = false, defaultValue = "1") int page
                            , @ModelAttribute("search") TicketVO search){
        // 티켓정보
        List<TicketDetailVO> ticketDetailList = ticketService.ticketDetailList();
        model.addAttribute("ticketDetail", ticketDetailList);
        // 티켓구매리스트 페이징
        int ticketAllCount = ticketService.ticketAllCount(search);
        Pagination pagination = new Pagination(ticketAllCount, page);
        List<TicketVO> ticketalllist = ticketService.ticketAllPaging(pagination, search);
        model.addAttribute("ticket", ticketalllist);
        model.addAttribute("page", pagination);
        // model.addAttribute("search", ticketVO);
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

    // 관리자페이지 회원리스트 페이지 이동 및 회원리스트 구현
    @GetMapping("/adminUser")
    public String UserList(Model model
                        , @RequestParam(name = "page", required = false, defaultValue = "1") int page){
        int userCount = userService.userCount();
        Pagination pagination = new Pagination(userCount, page);
        List<UserVO> userlist = userService.userList(pagination);
        // System.out.println(userlist); 
        model.addAttribute("user", userlist);
        model.addAttribute("page", pagination);
        return "/admin/adminUser";
    }

    // 회원등급업
    @PostMapping("/upGrade")
    public ResponseEntity<Boolean> userAuthUp(@RequestBody UserVO userVO){
        try{
            boolean result = userService.userAuthUp(userVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("관리자 권한 설정에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 회원등급다운
    @PostMapping("/downGrade")
    public ResponseEntity<Boolean> userAuthDown(@RequestBody UserVO userVO){
        try{
            boolean result = userService.userAuthDown(userVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("일반회원 권한 설정에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 회원블락
    @PostMapping("/userBlock")
    public ResponseEntity<Boolean> userBlock(@RequestBody UserVO userVO){
        try{
            boolean result = userService.userBlock(userVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("회원 블락처리에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 회원블락해제
    @PostMapping("/userNotBlock")
    public ResponseEntity<Boolean> userNotBlock(@RequestBody UserVO userVO){
        try{
            boolean result = userService.userNotBlock(userVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("회원 블락해제처리에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    
}

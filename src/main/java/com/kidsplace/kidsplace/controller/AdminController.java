package com.kidsplace.kidsplace.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kidsplace.kidsplace.commons.AdminNoticeVO;
import com.kidsplace.kidsplace.commons.AuthVO;
import com.kidsplace.kidsplace.commons.NoticeVO;
import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.QnaVO;
import com.kidsplace.kidsplace.commons.TicketDetailVO;
import com.kidsplace.kidsplace.commons.TicketVO;
import com.kidsplace.kidsplace.commons.UserVO;
import com.kidsplace.kidsplace.service.CommunityService;
import com.kidsplace.kidsplace.service.IpService;
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

    @Autowired
    CommunityService communityService;

    @Autowired
    IpService ipService;

    // 관리자페이지 메인페이지 이동
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String AdminPage(Model model){
        // 관리자 메인페이지 오늘 방문자집계
        int adminvisitsum = ipService.adminVisitSum();
        model.addAttribute("adminvisitsum", adminvisitsum);
        // 관리자 메인페이지 오늘 총계액
        List<TicketVO> adminbuysum = ticketService.adminBuySum();
        model.addAttribute("adminbuysum", adminbuysum);
        // 관리자 메인페이지 환불건수 데이터
        List<TicketVO> adminrefundsum = ticketService.adminRefundSum();
        model.addAttribute("adminrefundsum", adminrefundsum);
        // 관리자페이지 사내공지사항 리스트
        List<AdminNoticeVO> adminnotice = communityService.adminNotice();
        model.addAttribute("adminnotice", adminnotice);
        // 관리자페이지 Q&A 리스트
        List<QnaVO> adminqna = communityService.adminQna();
        model.addAttribute("adminqna", adminqna);
        return "admin/admin";
    }

    // 관리자페이지 티켓 리스트 페이지 이동 및 티켓리스트 구현
    @GetMapping("/adminTicket")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String TicketList(Model model
                            , @RequestParam(name = "page", required = false, defaultValue = "1") int page
                            , @ModelAttribute("search") TicketVO ticketVO
                            , @ModelAttribute("searchElse") UserVO userVO){
        // 티켓정보
        List<TicketDetailVO> ticketDetailList = ticketService.ticketDetailList();
        model.addAttribute("ticketDetail", ticketDetailList);
        // 티켓구매리스트 페이징
        int ticketAllCount = ticketService.ticketAllCount(ticketVO, userVO);
        Pagination pagination = new Pagination(ticketAllCount, page);
        List<TicketVO> ticketalllist = ticketService.ticketAllPaging(pagination, ticketVO, userVO);
        model.addAttribute("ticket", ticketalllist);
        model.addAttribute("page", pagination);
        // 검색조건 유지를 위한 값
        model.addAttribute("search", ticketVO);
        model.addAttribute("searchElse", userVO);
        // System.out.println(pagination.toString());
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String UserList(Model model
                        , @RequestParam(name = "page", required = false, defaultValue = "1") int page
                        , @ModelAttribute("search") UserVO userVO
                        , @ModelAttribute("searchElse") AuthVO authVO){
        int userCount = userService.userCount(userVO, authVO);
        Pagination pagination = new Pagination(userCount, page);
        List<UserVO> userlist = userService.userList(pagination, userVO, authVO);
        // System.out.println(userlist); 
        model.addAttribute("user", userlist);
        model.addAttribute("page", pagination);
        // 검색조건 유지를 위한 값
        model.addAttribute("search", userVO);
        model.addAttribute("searchElse", authVO);
        return "admin/adminUser";
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

    // 관리자페이지 티켓 리스트 페이지 이동 및 티켓정보리스트 구현
    @GetMapping("/adminTicketInfo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String TicketInfo(Model model){
        // 티켓정보
        List<TicketDetailVO> ticketDetailList = ticketService.ticketDetailList();
        model.addAttribute("ticketDetail", ticketDetailList);
        return "admin/adminTicketInfo";
    }

    // 티켓정보 삭제처리
    @PostMapping("/ticketInfoDelete")
    public ResponseEntity<Boolean> TicketInfoDelete(@RequestBody TicketDetailVO ticketDetailVO){
        try{
            boolean result = ticketService.ticketInfoDelete(ticketDetailVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("티켓정보삭제 요청에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 관리자페이지 티켓정보 추가 페이지 이동 및 티켓정보리스트 구현
    @GetMapping("/adminTicketInfoCreate")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String TicketInfoCreate(Model model){
        // 티켓정보
        List<TicketDetailVO> ticketDetailList = ticketService.ticketDetailList();
        model.addAttribute("ticketDetail", ticketDetailList);
        return "admin/adminTicketInfoCreate";
    }

    // 티켓정보 추가
    @PostMapping("/ticketInfoCreate")
    public ResponseEntity<Boolean> TicketInfoCreate(@RequestBody TicketDetailVO ticketDetailVO){
        try{
            boolean result = ticketService.ticketInfoCreate(ticketDetailVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("티켓정보 추가에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 티켓정보 수정
    @PostMapping("/ticketInfoEdit")
    public ResponseEntity<Boolean> TicketInfoEdit(@RequestBody TicketDetailVO ticketDetailVO){
        try{
            boolean result = ticketService.ticketInfoEdit(ticketDetailVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("티켓정보 수정에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 관리자페이지 정산페이지(일별) 이동
    @GetMapping("/adminCalculate")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String AdminCalculateDay(Model model
                                    , @ModelAttribute("search") TicketVO ticketVO){
        // 검색조건 유지를 위한 값
        model.addAttribute("search", ticketVO);
        // 티켓 리스트
        List<TicketVO> ticketdatelist = ticketService.ticketDateList(ticketVO);
        model.addAttribute("ticketdatelist", ticketdatelist);
        // 일별 구매 정산
        List<TicketVO> ticketdaybuysum = ticketService.ticketDayBuySum(ticketVO);
        model.addAttribute("ticketdaybuysum", ticketdaybuysum);
        // 일별 환불 정산
        List<TicketVO> ticketdayrefundsum = ticketService.ticketDayRefundSum(ticketVO);
        model.addAttribute("ticketdayrefundsum", ticketdayrefundsum);
        // 일별 수익 총계
        List<TicketVO> ticketdaytotal = ticketService.ticketDayTotal(ticketVO);
        model.addAttribute("ticketdaytotal", ticketdaytotal);
        return "admin/adminCalculate";
    }

    // 관리자페이지 정산페이지(월별) 이동
    @GetMapping("/adminCalculateMonth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String AdminCalculateMonth(Model model
                                    , @ModelAttribute("search") TicketVO ticketVO){
        // 검색조건 유지를 위한 값
        model.addAttribute("search", ticketVO);
        // 티켓 리스트
        List<TicketVO> ticketdatelist = ticketService.ticketDateList(ticketVO);
        model.addAttribute("ticketdatelist", ticketdatelist);
        // 월별 구매 정산
        List<TicketVO> ticketmonthlist = ticketService.ticketMonthList(ticketVO);
        model.addAttribute("ticketmonthlist", ticketmonthlist);
        // 월별 환불 정산
        List<TicketVO> ticketmonthrefund = ticketService.ticketMonthRefund(ticketVO);
        model.addAttribute("ticketmonthrefund", ticketmonthrefund);
        // 월별 수익 총계
        List<TicketVO> ticketmonthtotal = ticketService.ticketMonthTotal(ticketVO);
        model.addAttribute("ticketmonthtotal", ticketmonthtotal);
        return "admin/adminCalculateMonth";
    }

    // 관리자페이지 정산페이지(기간) 이동
    @GetMapping("/adminCalculateSince")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String AdminCalculateSince(Model model
                                    , @ModelAttribute("search") TicketVO ticketVO){
        // 검색조건 유지를 위한 값
        model.addAttribute("search", ticketVO);
        // 티켓 리스트
        List<TicketVO> ticketdatelist = ticketService.ticketSinceList(ticketVO);
        model.addAttribute("ticketdatelist", ticketdatelist);
        // 기간별 구매 정산
        List<TicketVO> ticketsincebuysum = ticketService.ticketSinceBuySum(ticketVO);
        model.addAttribute("ticketsincebuysum", ticketsincebuysum);
        // 기간별 환불 정산
        List<TicketVO> ticketsincerefundsum = ticketService.ticketSinceRefundSum(ticketVO);
        model.addAttribute("ticketsincerefundsum", ticketsincerefundsum);
        // 기간별 수익 총계
        List<TicketVO> ticketsincetotal = ticketService.ticketSinceTotal(ticketVO);
        model.addAttribute("ticketsincetotal", ticketsincetotal);
        return "admin/adminCalculateSince";
    }

    // 관리자페이지 사내공지사항 페이지 이동
    @GetMapping("/adminNotice")
    public String NoticeList (Model model
                            , @RequestParam(name = "page", required = false, defaultValue = "1") int page
                            , @ModelAttribute("search") AdminNoticeVO adminNoticeVO){
        // 임시 리스트
        // List<AdminNoticeVO> adminnoticelist = communityService.adminnoticeList();
        // model.addAttribute("adminnotice", adminnoticelist);
        // 페이징 처리
        int adminNoticeCount = communityService.adminNoticeCount(adminNoticeVO);
        Pagination pagination = new Pagination(adminNoticeCount, page);
        List<AdminNoticeVO> adminnoticelist = communityService.adminNoticePaging(pagination, adminNoticeVO);
        model.addAttribute("adminnotice", adminnoticelist);
        model.addAttribute("page", pagination);
        // 검색조건 유지를 위한 값
        model.addAttribute("search", adminNoticeVO);
        System.out.println(pagination.toString());
        return "admin/adminNotice";
    }

    // 관리자페이지 사내공지사항작성 페이지 이동
    @GetMapping("/adminNoticeWrite")
    public String AdminNoticeWirte (Model model){
        return "admin/adminNoticeWrite";
    }

    // 관리자페이지 사내공지사항작성
    @PostMapping("/adminNoticeWrite")
    public ResponseEntity<Boolean> adminNoticeWrite(@RequestBody AdminNoticeVO adminNoticeVO){
        try{
            communityService.adminNoticeWrite(adminNoticeVO);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e){
            logger.error("사내공지사항 정보 저장에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 관리자페이지 사내공지사항상세 페이지 이동
    @GetMapping("/adminNoticeDetail")
    public String AdminNoticeDetail (int aNum, Model model){
       // logger.warn(String.valueOf(aNum));
        AdminNoticeVO adminNoticeVO = communityService.adminNoticeRead(aNum);
        model.addAttribute("adminNoticeDetail", adminNoticeVO);
        return "admin/adminNoticeDetail";
    }

    // 관리자페이지 사내공지사항수정 페이지 이동
    @GetMapping("/adminNoticeEdit")
    public String AdminNoticeEdit (int aNum, Model model){
       // logger.warn(String.valueOf(aNum));
        AdminNoticeVO adminNoticeVO = communityService.adminNoticeRead(aNum);
        model.addAttribute("adminNoticeDetail", adminNoticeVO);
        return "admin/adminNoticeEdit";
    }

    // 관리자페이지 사내공지사항수정
    @PostMapping("/adminNoticeEdit")
    public ResponseEntity<Boolean> adminNoticeEdit(@RequestBody AdminNoticeVO adminNoticeVO){
        try{
            boolean result = communityService.adminNoticeEdit(adminNoticeVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("사내공지사항 정보 수정에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 관리자페이지 사내공지사항삭제
    @PostMapping("/adminNoticeDelete")
    public ResponseEntity<Boolean> adminNoticeDelete(@RequestBody AdminNoticeVO adminNoticeVO){
        try{
            boolean result = communityService.adminNoticeDelete(adminNoticeVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("사내공지사항 정보 삭제에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    
}

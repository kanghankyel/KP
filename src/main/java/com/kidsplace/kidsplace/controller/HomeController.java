package com.kidsplace.kidsplace.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.kidsplace.kidsplace.commons.FaqVO;
import com.kidsplace.kidsplace.commons.NoticeVO;
import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.service.CommunityService;
import com.kidsplace.kidsplace.service.IpService;

@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CommunityService communityService;

    @Autowired
    IpService ipService;
    
    // main home 화면
    @GetMapping(value = "/")
    public String home(Model model
                    , HttpServletRequest request
                    , @RequestParam(name = "page", required = false, defaultValue = "1") int page
                    , @ModelAttribute("search") NoticeVO noticeVO){
        // 방문자 ip주소 주입
        String clientIp = request.getRemoteAddr();
        logger.info("방문자의 IP 주소: " + clientIp);
        ipService.insertIp(clientIp);
        // 공지사항 리스트
        int noticeCount = communityService.noticeCount(noticeVO);
        Pagination pagination = new Pagination(noticeCount, page);
        List<NoticeVO> noticelist = communityService.noticePaging(pagination, noticeVO);
        model.addAttribute("notice", noticelist);
        // faq 리스트
        int faqCount = communityService.faqCount();
        Pagination paginationFaq = new Pagination(faqCount, page);
        List<FaqVO> faqlist = communityService.faqPagingHome(paginationFaq);
        model.addAttribute("faq", faqlist);
        return "page/home";
    }
    

    // 소개 페이지 이동
    @GetMapping("/info")
    public String Info(){
        return "page/info";
    }

    // 지점 위치 페이지 이동
    @GetMapping("/location")
    public String Location(){
        return "page/location";
    }

}

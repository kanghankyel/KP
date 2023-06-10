package com.kidsplace.kidsplace.controller;

import java.util.List;

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

@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CommunityService communityService;
    
    // main home 화면
    @GetMapping(value = "/")
    public String home(Model model
                    , @RequestParam(name = "page", required = false, defaultValue = "1") int page
                    , @ModelAttribute("search") NoticeVO noticeVO){
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
        return "/page/home";
    }

    // 소개 페이지 이동
    @GetMapping("/info")
    public String Info(){
        return "/page/info";
    }

    // 지점 위치 페이지 이동
    @GetMapping("/location")
    public String Location(){
        return "/page/location";
    }

}

package com.kidsplace.kidsplace.controller;

import com.kidsplace.kidsplace.commons.NoticeVO;
import com.kidsplace.kidsplace.service.CommunityService;
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

import java.util.List;

@Controller
@RequestMapping("community")
public class CommunityController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CommunityService communityService;

    // 공지사항 페이지 이동 및 리스트 구현
    @GetMapping("/notice")
    public String NoticeList (Model model){
        List<NoticeVO> noticelist = communityService.noticeList();
        model.addAttribute("notice", noticelist);
        return "/community/notice";
    }

    // 공지사항 항목 개별 선택(공지사항 보기)
    @GetMapping("/noticeDetail")
    public String NoticeDetail (int nNum, Model model){
       // logger.warn(String.valueOf(nNum));
        NoticeVO noticeVO = communityService.noticeRead(nNum);
        model.addAttribute("noticeDetail", noticeVO);
        return "/community/noticeDetail";
    }

    // 공지사항 쓰기 페이지 이동
    @GetMapping("/noticeWrite")
    public String noticeWrite(){
        return "/community/noticeWrite";
    }

    // 공지사항 쓰기
    @PostMapping("/noticeWrite")
    public ResponseEntity<Boolean> noticeWrite(@RequestBody NoticeVO noticeVO){
        try{
            communityService.noticeWrite(noticeVO);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e){
            logger.error("공지사항 정보 저장에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 공지사항 수정 페이지 이동 및 해당 정보 출력
    @GetMapping("/noticeEdit")
    public String NoticeEdit (int nNum, Model model){
       // logger.warn(String.valueOf(nNum));
        NoticeVO noticeVO = communityService.noticeRead(nNum);
        model.addAttribute("noticeDetail", noticeVO);
        return "/community/noticeEdit";
    }

    // 공지사항 수정 데이터베이스 반영
    @PostMapping("/noticeEdit")
    public ResponseEntity<Boolean> noticeEdit(@RequestBody NoticeVO noticeVO){
        try{
            boolean result = communityService.noticeEdit(noticeVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("공지사항 정보 수정에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 공지사항 삭제
    @PostMapping("/noticeDelete")
    public ResponseEntity<Boolean> noticeDelete(@RequestBody NoticeVO noticeVO){
        try{
            boolean result = communityService.noticeDelete(noticeVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("공지사항 정보 삭제에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

}

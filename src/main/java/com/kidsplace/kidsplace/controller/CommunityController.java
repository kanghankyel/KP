package com.kidsplace.kidsplace.controller;

import com.kidsplace.kidsplace.commons.AdminNoticeVO;
import com.kidsplace.kidsplace.commons.FaqVO;
import com.kidsplace.kidsplace.commons.NoticeVO;
import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.QnaVO;
import com.kidsplace.kidsplace.service.CommunityService;
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

import java.util.List;

@Controller
@RequestMapping("community")
public class CommunityController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CommunityService communityService;

    // 공지사항 페이지 이동 및 리스트 구현
    @GetMapping("/notice")
    public String NoticeList (Model model
                            , @RequestParam(name = "page", required = false, defaultValue = "1") int page
                            , @ModelAttribute("search") NoticeVO noticeVO){
        // 임시 리스트
        // List<NoticeVO> noticelist = communityService.noticeList();
        // model.addAttribute("notice", noticelist);
        // 아래는 페이징 처리
        int noticeCount = communityService.noticeCount(noticeVO);
        Pagination pagination = new Pagination(noticeCount, page);
        List<NoticeVO> noticelist = communityService.noticePaging(pagination, noticeVO);
        model.addAttribute("notice", noticelist);
        model.addAttribute("page", pagination);
        // 검색조건 유지를 위한 값
        model.addAttribute("search", noticeVO);
        System.out.println(pagination.toString());
        return "community/notice";
    }

    // 공지사항 항목 개별 선택(공지사항 보기)
    @GetMapping("/noticeDetail")
    public String NoticeDetail (int nNum, Model model){
       // logger.warn(String.valueOf(nNum));
        NoticeVO noticeVO = communityService.noticeRead(nNum);
        model.addAttribute("noticeDetail", noticeVO);
        return "community/noticeDetail";
    }

    // 공지사항 쓰기 페이지 이동
    @GetMapping("/noticeWrite")
    public String noticeWrite(){
        return "community/noticeWrite";
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
        return "community/noticeEdit";
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

    // FAQ 페이지 이동 및 리스트 구현
    @GetMapping("/faq")
    public String FaqList (Model model
                            , @RequestParam(name = "page", required = false, defaultValue = "1") int page){
        int faqCount = communityService.faqCount();
        Pagination pagination = new Pagination(faqCount, page);
        List<FaqVO> faqlist = communityService.faqPaging(pagination);
        model.addAttribute("faq", faqlist);
        model.addAttribute("page", pagination);
        return "community/faq";
    }

    // FAQ 쓰기 페이지 이동
    @GetMapping("/faqWrite")
    public String faqWrite(){
        return "community/faqWrite";
    }

    // FAQ 쓰기
    @PostMapping("/faqWrite")
    public ResponseEntity<Boolean> faqWrite(@RequestBody FaqVO faqVO){
        try{
            boolean result = communityService.faqWrite(faqVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("FAQ 추가에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // FAQ 삭제처리
    @PostMapping("/faqDelete")
    public ResponseEntity<Boolean> FaqDelete(@RequestBody FaqVO faqVO){
        try{
            boolean result = communityService.faqDelete(faqVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("FAQ 삭제에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // Q&A 페이지 이동 및 리스트 구현
    @GetMapping("/qna")
    public String Qna (Model model
                    , @RequestParam(name = "page", required = false, defaultValue = "1") int page
                    , @ModelAttribute("search") QnaVO qnaVO){
        // 임시 리스트
        // List<QnaVO> noticelist = communityService.qnaList();
        // model.addAttribute("qna", qnalist);
        // 페이징 처리
        int qnaCount = communityService.qnaCount(qnaVO);
        Pagination pagination = new Pagination(qnaCount, page);
        List<QnaVO> qnalist = communityService.qnaPaging(pagination, qnaVO);
        model.addAttribute("qna", qnalist);
        model.addAttribute("page", pagination);
        // 검색조건 유지를 위한 값
        model.addAttribute("search", qnaVO);
        // System.out.println(pagination.toString());
        return "community/qna";
    }

    // Q&A 작성 페이지 이동
    @GetMapping("/qnaWrite")
    public String QnaWrite(){
        return "community/qnaWrite";
    }

    // Q&A 작성
    @PostMapping("/qnaWrite")
    public ResponseEntity<Boolean> qnaWrite(@RequestBody QnaVO qnaVO){
        try{
            communityService.qnaWrite(qnaVO);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e){
            logger.error("Q&A 정보 저장에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // Q&A 상세 페이지 이동
    @GetMapping("/qnaDetail")
    public String QnaDetail (int qNum, Model model){
       // logger.warn(String.valueOf(qNum));
        QnaVO qnaVO = communityService.qnaRead(qNum);
        model.addAttribute("qnaDetail", qnaVO);
        return "community/qnaDetail";
    }

    // Q&A 삭제처리
    @PostMapping("/qnaDelete")
    public ResponseEntity<Boolean> QaqDelete(@RequestBody QnaVO qnaVO){
        try{
            boolean result = communityService.qaqDelete(qnaVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("Q&A 삭제에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // Q&A 답변작성 페이지 이동
    @GetMapping("/qnaAnswer")
    public String QnaAnswer(int qNum, Model model){
        QnaVO qnaVO = communityService.qnaRead(qNum);
        model.addAttribute("qnaDetail", qnaVO);
        return "community/qnaAnswer";
    }

    // Q&A 답변작성
    @PostMapping("/qnaAnswer")
    public ResponseEntity<Boolean> qnaAnswer(@RequestBody QnaVO qnaVO){
        try{
            boolean result = communityService.qnaAnswer(qnaVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("Q&A 답변 정보 저장에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // Q&A 답변삭제
    @PostMapping("/qnaAnswerDelete")
    public ResponseEntity<Boolean> qnaAnswerDelete(@RequestBody QnaVO qnaVO){
        try{
            boolean result = communityService.qnaAnswerDelete(qnaVO);
            if(result){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            logger.error("Q&A 답변 삭제에 실패하였습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }


}

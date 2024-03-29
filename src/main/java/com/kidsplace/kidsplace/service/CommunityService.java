package com.kidsplace.kidsplace.service;

import com.kidsplace.kidsplace.commons.AdminNoticeVO;
import com.kidsplace.kidsplace.commons.FaqVO;
import com.kidsplace.kidsplace.commons.NoticeVO;
import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.QnaVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommunityService {

    // 공지사항 리스트 불러오기
    // List<NoticeVO> noticeList();

    // 공지사항 리스트 페이징
    List<NoticeVO> noticePaging(Pagination pagination, NoticeVO noticeVO);

    // 공지사항 게시글 카운팅
    int noticeCount(NoticeVO noticeVO);

    // 공지사항 항목 하나 불러오기(공지사항 보기)
    NoticeVO noticeRead(@Param("nNum") int nNum);

    // 공지사항 쓰기
    void noticeWrite(NoticeVO noticeVO);

    // 공지사항 수정
    Boolean noticeEdit(NoticeVO noticeVO);

    // 공지사항 삭제
    Boolean noticeDelete(NoticeVO noticeVO);

    // FAQ 리스트 페이징
    List<FaqVO> faqPaging(Pagination pagination);

    // FAQ 게시글 카운팅
    int faqCount();

    // FAQ 쓰기
    boolean faqWrite(FaqVO faqVO);

    // FAQ 삭제
    boolean faqDelete(FaqVO faqVO);

    // HOME faq
    List<FaqVO> faqPagingHome(Pagination pagination);

    // 사내공지사항 페이징
    List<AdminNoticeVO> adminNoticePaging(Pagination pagination, AdminNoticeVO adminNoticeVO);

    // 사내공지사항 카운팅
    int adminNoticeCount(AdminNoticeVO adminNoticeVO);

    // 사내공지사항 작성
    void adminNoticeWrite(AdminNoticeVO adminNoticeVO);

    // 사내공지사항 상세
    AdminNoticeVO adminNoticeRead(int aNum);

    // 사내공지사항 수정
    Boolean adminNoticeEdit(AdminNoticeVO adminNoticeVO);

    // 사내공지사항 삭제
    boolean adminNoticeDelete(AdminNoticeVO adminNoticeVO);

    // 관리자페이지 사내공지사항 리스트
    List<AdminNoticeVO> adminNotice();

    // 관리자페이지 Q&A 리스트
    List<QnaVO> adminQna();

    // Qna 리스트 페이징
    List<QnaVO> qnaPaging(Pagination pagination, QnaVO qnaVO);

    // Qna 리스트 카운팅
    int qnaCount(QnaVO qnaVO);

    // Qna 작성
    void qnaWrite(QnaVO qnaVO);

    // Qna 상세
    QnaVO qnaRead(int qNum);

    // Qna 삭제
    boolean qaqDelete(QnaVO qnaVO);

    // Qna 답변작성
    boolean qnaAnswer(QnaVO qnaVO);

    // Qna 답변삭제
    boolean qnaAnswerDelete(QnaVO qnaVO);

}

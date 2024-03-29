package com.kidsplace.kidsplace.dao;

import com.kidsplace.kidsplace.commons.AdminNoticeVO;
import com.kidsplace.kidsplace.commons.FaqVO;
import com.kidsplace.kidsplace.commons.NoticeVO;
import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.commons.QnaVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommunityDAO {

    // 공지사항 리스트 불러오기
    // List<NoticeVO> noticeList();

    // 공지사항 게시글 리스트 조회 페이징
    List<NoticeVO> noticePaging(@Param("pagination") Pagination pagination, @Param("noticeVO") NoticeVO noticeVO) throws Exception;

    // 공지사항 게시글 카운팅
    int noticeCount(@Param("noticeVO") NoticeVO noticeVO);

    // 공지사항 항목 하나 불러오기(공지사항 보기)
    NoticeVO noticeRead(int nNum);

    // 공지사항 쓰기
    void noticeWrite(NoticeVO noticeVO);

    // 공지사항 수정
    int noticeEdit(NoticeVO noticeVO) throws Exception;

    // 공지사항 삭제
    int noticeDelete(NoticeVO noticeVO) throws Exception;

    // FAQ 리스트 페이징
    List<FaqVO> faqPaging(@Param("pagination")Pagination pagination) throws Exception;

    // FAQ 게시글 카운팅
    int faqCount();

    // FAQ 쓰기
    int faqWrite(FaqVO faqVO) throws Exception;

    // FAQ 삭제
    int faqDelete(FaqVO faqVO) throws Exception;

    // 사내공지사항 페이징
    List<AdminNoticeVO> adminNoticePaging(@Param("pagination") Pagination pagination, @Param("adminNoticeVO") AdminNoticeVO adminNoticeVO) throws Exception;

    // 사내공지사항 카운팅
    int adminNoticeCount(@Param("adminNoticeVO") AdminNoticeVO adminNoticeVO);

    // 사내공지사항 작성
    void adminNoticeWrite(AdminNoticeVO adminNoticeVO);

    // 사내공지사항 상세
    AdminNoticeVO adminNoticeRead(int aNum);

    // 사내공지사항 수정
    int adminNoticeEdit(AdminNoticeVO adminNoticeVO) throws Exception;

    // 사내공지사항 삭제
    int adminNoticeDelete(AdminNoticeVO adminNoticeVO) throws Exception;

    // 관리자페이지 사내공지사항 리스트
    List<AdminNoticeVO> adminNotice() throws Exception;

    // 관리자페이지 Q&A 리스트
    List<QnaVO> adminQna() throws Exception;

    // Qna 리스트 페이징
    List<QnaVO> qnaPaging(@Param("pagination") Pagination pagination, @Param("qnaVO") QnaVO qnaVO) throws Exception;

    // Qna 리스트 카운팅
    int qnaCount(@Param("qnaVO") QnaVO qnaVO);

    // Qna 작성
    void qnaWrite(QnaVO qnaVO);

    // Qna 상세
    QnaVO qnaRead(int qNum);

    // Qna 삭제
    int qnaDelete(QnaVO qnaVO) throws Exception;

    // Qna 답변작성
    int qnaAnswer(QnaVO qnaVO) throws Exception;

    // Qna 답변삭제
    int qnaAnswerDelete(QnaVO qnaVO) throws Exception;

}

package com.kidsplace.kidsplace.dao;

import com.kidsplace.kidsplace.commons.FaqVO;
import com.kidsplace.kidsplace.commons.NoticeVO;
import com.kidsplace.kidsplace.commons.Pagination;

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
    List<NoticeVO> noticePaging(@Param("pagination") Pagination pagination, @Param("noticeVO") NoticeVO noticeVO);

    // 공지사항 게시글 카운팅
    int noticeCount(@Param("noticeVO") NoticeVO noticeVO);

    // 공지사항 항목 하나 불러오기(공지사항 보기)
    NoticeVO noticeRead(int nNum);

    // 공지사항 쓰기
    void noticeWrite(NoticeVO noticeVO);

    // 공지사항 수정
    int noticeEdit(NoticeVO noticeVO);

    // 공지사항 삭제
    int noticeDelete(NoticeVO noticeVO);

    // FAQ 리스트 페이징
    List<FaqVO> faqPaging(@Param("pagination")Pagination pagination);

    // FAQ 게시글 카운팅
    int faqCount();

    // FAQ 쓰기
    int faqWrite(FaqVO faqVO);

    // FAQ 삭제
    int faqDelete(FaqVO faqVO);

}

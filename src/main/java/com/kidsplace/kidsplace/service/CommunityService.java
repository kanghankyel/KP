package com.kidsplace.kidsplace.service;

import com.kidsplace.kidsplace.commons.NoticeVO;
import com.kidsplace.kidsplace.commons.Pagination;

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

}

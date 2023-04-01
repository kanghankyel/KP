package com.kidsplace.kidsplace.dao;

import com.kidsplace.kidsplace.commons.NoticeVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommunityDAO {

    // 공지사항 리스트 불러오기
    List<NoticeVO> noticeList();

    // 공지사항 항목 하나 불러오기(공지사항 보기)
    NoticeVO noticeRead(int nNum);

    // 공지사항 쓰기
    void noticeWrite(NoticeVO noticeVO);

    // 공지사항 수정
    int noticeEdit(NoticeVO noticeVO);

    // 공지사항 삭제
    int noticeDelete(NoticeVO noticeVO);
}

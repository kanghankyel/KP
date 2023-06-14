package com.kidsplace.kidsplace.service;

import com.kidsplace.kidsplace.commons.FaqVO;
import com.kidsplace.kidsplace.commons.NoticeVO;
import com.kidsplace.kidsplace.commons.Pagination;
import com.kidsplace.kidsplace.dao.CommunityDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class CommunityServicempl implements CommunityService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommunityDAO communityDAO;

    // @Override
    // public List<NoticeVO> noticeList() {
    //     return communityDAO.noticeList();
    // }

    @Override
    public List<NoticeVO> noticePaging(Pagination pagination, NoticeVO noticeVO) {
        // logger.info("get List with SearchDTO");
        // return communityDAO.noticePaging(pagination, noticeVO);
        try {
            return communityDAO.noticePaging(pagination, noticeVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public int noticeCount(NoticeVO noticeVO) {
        // System.out.println("noticeCount : " + communityDAO.noticeCount());
        return communityDAO.noticeCount(noticeVO);
    }

    @Override
    public NoticeVO noticeRead(int nNum) {
       // logger.warn(String.valueOf(nNum));
        NoticeVO noticeVO = communityDAO.noticeRead(nNum);
        return noticeVO;
    }

    @Override
    @Transactional
    public void noticeWrite(NoticeVO noticeVO) {
        communityDAO.noticeWrite(noticeVO);
    }

    @Override
    @Transactional
    public Boolean noticeEdit(NoticeVO noticeVO) {
        // logger.warn("공지사항 수정 데이터");
        // logger.warn(String.valueOf(noticeVO));
        try{
            int result = communityDAO.noticeEdit(noticeVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("공지사항 수정 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean noticeDelete(NoticeVO noticeVO) {
        // logger.warn("공지사항 삭제 데이터");
        // logger.warn(String.valueOf(noticeVO));
        try{
            int result = communityDAO.noticeDelete(noticeVO);
            if (result > 0){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            logger.error("공지사항 삭제 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<FaqVO> faqPaging(Pagination pagination) {
        // logger.info("get List with FaqDTO");
        // return communityDAO.faqPaging(pagination);
        try {
            return communityDAO.faqPaging(pagination);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public int faqCount() {
        int result = communityDAO.faqCount();
        System.out.println("faqCount : " + result);
        return result;
    }

    @Override
    public boolean faqWrite(FaqVO faqVO) {
        // logger.warn("FAQ 추가처리 데이터");
        // logger.warn(String.valueOf(faqVO));
        try{
            int result = communityDAO.faqWrite(faqVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("FAQ 추가처리 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean faqDelete(FaqVO faqVO) {
        // logger.warn("FAQ 삭제처리 데이터");
        // logger.warn(String.valueOf(faqVO));
        try{
            int result = communityDAO.faqDelete(faqVO);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            logger.error("FAQ 삭제 데이터 오류");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<FaqVO> faqPagingHome(Pagination pagination) {
        // logger.info("get List with FaqDTO");
        // return communityDAO.faqPaging(pagination);
        try {
            return communityDAO.faqPaging(pagination);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}

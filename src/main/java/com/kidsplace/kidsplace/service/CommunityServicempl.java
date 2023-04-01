package com.kidsplace.kidsplace.service;

import com.kidsplace.kidsplace.commons.NoticeVO;
import com.kidsplace.kidsplace.dao.CommunityDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommunityServicempl implements CommunityService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommunityDAO communityDAO;

    @Override
    public List<NoticeVO> noticeList() {
        return communityDAO.noticeList();
    }

    @Override
    public NoticeVO noticeRead(int nNum) {
//        logger.warn(String.valueOf(nNum));
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
        logger.warn("공지사항 수정 데이터");
        logger.warn(String.valueOf(noticeVO));
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
        logger.warn("공지사항 삭제 데이터");
        logger.warn(String.valueOf(noticeVO));
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

}

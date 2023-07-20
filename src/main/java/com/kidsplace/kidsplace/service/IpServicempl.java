package com.kidsplace.kidsplace.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kidsplace.kidsplace.dao.IpDAO;

@Service
public class IpServicempl implements IpService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IpDAO ipDAO;

    @Override
    public void insertIp(String clientIp) {
        // logger.warn("IP 추가처리 데이터");
        ipDAO.insertIp(clientIp);
        logger.info("service단에서 호출된 ip: " + clientIp);
    }

    @Override
    public int adminVisitSum() {
        int result = ipDAO.adminVisitSum();
        System.out.println("adminVisitSum : " + result);
        return result;
    }

}

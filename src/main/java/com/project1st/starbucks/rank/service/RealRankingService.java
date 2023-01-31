package com.project1st.starbucks.rank.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project1st.starbucks.rank.repository.RealRankingRepository;

@Service
public class RealRankingService {
    @Autowired RealRankingRepository realRankRepo;

    public ResponseEntity<Object> storeMenuList()  {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("list", realRankRepo.findAll());
        return new ResponseEntity<Object>(resultMap, HttpStatus.OK);
    }
}

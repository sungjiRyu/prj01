package com.project1st.starbucks.rank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project1st.starbucks.rank.service.RealRankingService;

@RestController
public class RealRankingController {
    @Autowired RealRankingService realRankingService;
    @GetMapping("/menu/rank")
    public ResponseEntity<Object> menuRanking() {
        return realRankingService.storeMenuList();
    }
}

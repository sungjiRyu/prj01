package com.project1st.starbucks.map.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project1st.starbucks.map.repository.StoreGPSRepository;

@RestController
@CrossOrigin("*")

public class StoreGPSController {
    @Autowired StoreGPSRepository sGpsRepo;
    @GetMapping("/gps/list")
    public Map<String, Object> basket()  {        
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("list", sGpsRepo.findAll());
        return resultMap;
    }

    @GetMapping("/gps/search")
    public Map<String, Object> gpsSearch(@RequestParam String keyword)  {        
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("list", sGpsRepo.findGPS(keyword));
        return resultMap;
    }
}

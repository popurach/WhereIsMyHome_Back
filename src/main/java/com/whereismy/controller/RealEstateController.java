package com.whereismy.controller;

import com.whereismy.service.RealEstateService;
import com.whereismy.vo.RealEstateDTO;
import com.whereismy.vo.ResponseDTO;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Api(tags = {"land"})
@RestController
@RequiredArgsConstructor
@RequestMapping("land")

public class RealEstateController {
	private final RealEstateService realEstateService;

    @GetMapping("news")
    public ResponseEntity<ResponseDTO> getRealEstateNews() {
        ResponseDTO res = new ResponseDTO();
        LocalDate date = LocalDate.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd"); // 날짜 변환 
        String today = date.format(formatter); // 오늘 날짜 string으로 뽑기
        List<RealEstateDTO> list = realEstateService.getRealEstateNewsList(today);
        res.setBody(list);
        res.setStatus("success");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

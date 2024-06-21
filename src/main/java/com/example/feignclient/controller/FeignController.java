package com.example.feignclient.controller;

import com.example.feignclient.service.NaverPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class FeignController {

    //private final NaverPayService naverPayService;
/*
    //네이버페이 결제 승인 요청
    @PostMapping(value = "/naver/payApproval")
    public ResponseEntity naverPayApproval() {
        return naverPayService.payApproval();
    }

 */
}

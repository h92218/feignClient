package com.example.feignclient.controller;

import com.example.feignclient.service.NaverPayService;
import com.example.feignclient.service.TestFeignService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class FeignController {

    private final NaverPayService naverPayService;
    private final TestFeignService testFeignService;

    //네이버페이 결제 승인 요청
    @PostMapping(value = "/naver/payApproval")
    public ResponseEntity naverPayApproval(@RequestBody Map<String,String> param) {
        return naverPayService.payApproval(param.getOrDefault("paymentId",""));
    }

    //네이버페이 결제 취소 요청
    @PostMapping(value = "/naver/payCancel")
    public ResponseEntity naverPayCancel(@RequestBody Map<String,String> param) {
        return naverPayService.payCancel(param.getOrDefault("paymentId",""));
    }

    //네이버페이 결제 승인 요청
    @PostMapping(value = "/naver/payApprovalByRequestLine")
    public ResponseEntity payApprovalByRequestLine(@RequestBody Map<String,String> param) throws Exception{
        return testFeignService.payApprovalByRequestLine(param.getOrDefault("paymentId",""));
    }

}

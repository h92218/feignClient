package com.example.feignclient.service;

import com.example.feignclient.util.DynamicUrlTestFeignClient;
import feign.Feign;
import feign.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DynamicUrlTestFeignService {
    @Value("${naver.clientId}")
    private String CLIENT_ID;

    @Value("${naver.clientSecret}")
    private String CLIENT_SECRET;

    @Value("${naver.chainId}")
    private String CHAIN_ID;

    @Value("${naver.url}")
    String NAVER_URL;

    public ResponseEntity payApprovalByRequestLine(String paymentId) throws Exception{
        DynamicUrlTestFeignClient testFeignClient = Feign.builder().target(DynamicUrlTestFeignClient.class,NAVER_URL+"/v2.2/apply/payment");
        Map <String,Object> paramMap = new HashMap<>();
        paramMap.put("paymentId",paymentId);
        Response response = testFeignClient.approvalRequest(this.header(),paramMap);
        String responseBody = new String(response.body().asInputStream().readAllBytes());


        return new ResponseEntity(responseBody,HttpStatusCode.valueOf(200));
    }

    public Map<String,String> header(){
        Map<String, String> headerMap = new HashMap<String, String>();

        headerMap.put("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headerMap.put("X-Naver-Client-Id", CLIENT_ID);
        headerMap.put("X-Naver-Client-Secret", CLIENT_SECRET);
        headerMap.put("X-NaverPay-Chain-Id", CHAIN_ID);
        headerMap.put("X-NaverPay-Idempotency-Key", UUID.randomUUID().toString()); //멱등성 키

        return headerMap;

    }
}

package com.example.feignclient.service;

import com.example.feignclient.util.TestFeignClient;
import feign.Feign;
import feign.Response;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TestFeignService {
    @Value("${naver.clientId}")
    private String clientId;

    @Value("${naver.clientSecret}")
    private String clientSecret;

    @Value("${naver.chainId}")
    private String chainId;


    public ResponseEntity payApprovalByRequestLine(String paymentId) throws Exception{
        TestFeignClient testFeignClient = Feign.builder().target(TestFeignClient.class,"https://dev.apis.naver.com/naverpay-partner/naverpay/payments/v2.2/apply/payment");
        Map <String,Object> paramMap = new HashMap<>();
        paramMap.put("paymentId",paymentId);
        Response response = testFeignClient.approvalRequest(this.header(),paramMap);
        String responseBody = new String(response.body().asInputStream().readAllBytes());


        return new ResponseEntity(responseBody,HttpStatusCode.valueOf(200));
    }

    public Map<String,String> header(){
        Map<String, String> headerMap = new HashMap<String, String>();

        headerMap.put("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headerMap.put("X-Naver-Client-Id", clientId);
        headerMap.put("X-Naver-Client-Secret", clientSecret);
        headerMap.put("X-NaverPay-Chain-Id", chainId);
        headerMap.put("X-NaverPay-Idempotency-Key", UUID.randomUUID().toString()); //멱등성 키

        return headerMap;

    }
}

package com.example.feignclient.service;

import com.example.feignclient.util.NaverFeignClient;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class NaverPayService {

    @Value("${naver.clientId}")
    private String clientId;

    @Value("${naver.clientSecret}")
    private String clientSecret;

    @Value("${naver.chainId}")
    private String chainId;

    private final NaverFeignClient feignClient;

    public ResponseEntity payApproval(String paymentId){
        JSONObject response = feignClient.approvalRequest(this.header(),paymentId);
        return new ResponseEntity(response, HttpStatusCode.valueOf(200));
    }



    public ResponseEntity payCancel(String paymentId){
        Map<String,Object> param = new HashMap<>();
        param.put("paymentId",paymentId);
        param.put("cancelAmount",10);
        param.put("cancelReason","test");
        param.put("cancelRequester","2");
        param.put("taxScopeAmount",10);
        param.put("taxExScopeAmount",0);

        JSONObject response = feignClient.cancelRequest(this.header(),param);
        return new ResponseEntity(response, HttpStatusCode.valueOf(200));
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

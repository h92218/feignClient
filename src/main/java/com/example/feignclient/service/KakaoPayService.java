package com.example.feignclient.service;

import com.example.feignclient.util.KakaoFeignClient;
import com.example.feignclient.util.NaverFeignClient;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoPayService {
    @Value("${kakao.secretKey}")
    private String SECRET_KEY;

    private final KakaoFeignClient feignClient;

    public ResponseEntity ready() throws Exception {
        Map<String, Object> result;

        Map<String,Object> kakaoMap = new HashMap<>();
        kakaoMap.put("cid","TC0ONETIME");
        kakaoMap.put("partner_order_id","testOrderId");
        kakaoMap.put("partner_user_id","testUserId");
        kakaoMap.put("item_name","testItem");
        kakaoMap.put("quantity",1);
        kakaoMap.put("total_amount",1000);
        kakaoMap.put("tax_free_amount",0);
        kakaoMap.put("approval_url","http://localhost:8081/approve");
        kakaoMap.put("cancel_url","http://localhost:8081/cancel");
        kakaoMap.put("fail_url","http://localhost:8081/fail");


        JSONObject responseEntity = feignClient.ready("SECRET_KEY " + SECRET_KEY, kakaoMap);

        return new ResponseEntity(responseEntity, HttpStatusCode.valueOf(200));

    }
}

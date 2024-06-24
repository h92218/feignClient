package com.example.feignclient.util;

import feign.*;
import net.sf.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.Map;

//RequestLine 사용한 동적 URL 테스트
public interface TestFeignClient {

    @RequestLine("POST")
    Response approvalRequest(@HeaderMap Map<String,String> header, @QueryMap Map<String, Object> queryParams);
}

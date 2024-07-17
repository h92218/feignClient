package com.example.feignclient.util;

import feign.*;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.Map;

//RequestLine 사용한 동적 URL 테스트
@FeignClient(name="dynamicFeign")
public interface DynamicUrlTestFeignClient {

    @RequestLine("POST")
    Response approvalRequest(@HeaderMap Map<String,String> header, @QueryMap Map<String, Object> queryParams);
}

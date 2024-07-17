package com.example.feignclient.util;

import net.sf.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "kakaoFeignClient", url="${kakao.url}")
public interface KakaoFeignClient {

    @PostMapping(value = "/ready")
    JSONObject ready(@RequestHeader("Authorization") String authorization_key, Map<String,Object> kakaoMap);

    @PostMapping(value = "/approve")
    JSONObject approve(@RequestHeader("Authorization") String authorization_key, Map<String,Object> kakaoMap);

    @PostMapping(value = "/cancel")
    JSONObject cancel(@RequestHeader("Authorization") String authorization_key, Map<String,Object> kakaoMap);

}

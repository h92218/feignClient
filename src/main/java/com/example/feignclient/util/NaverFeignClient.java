package com.example.feignclient.util;

import net.sf.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name="naverFeignClient", url="${naver.url}")
public interface NaverFeignClient {

    @PostMapping(value="/v2.2/apply/payment")
    JSONObject approvalRequest(@RequestHeader Map<String,String> header, @RequestParam String paymentId);

    @PostMapping(value="/v1/cancel", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    JSONObject cancelRequest(@RequestHeader Map<String,String> header, @RequestBody Map<String, ?> form);


    @PostMapping(value="/v1/cancel",  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    JSONObject cancelRequest2(@RequestHeader Map<String,String> header,
                              @RequestParam String paymentId,
                              @RequestParam int cancelAmount,
                              @RequestParam String cancelReason,
                              @RequestParam String cancelRequester,
                              @RequestParam int taxScopeAmount,
                              @RequestParam int taxExScopeAmount);
}

package br.com.windevs.checkformebot.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "client", url = "http://www.mocky.io")
public interface StoreClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v2/5ed301423400005f0001f1f6")
    Mock getMock();
}
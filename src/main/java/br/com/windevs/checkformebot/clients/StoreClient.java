package br.com.windevs.checkformebot.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "client", url = "http://www.mocky.io")
public interface StoreClient {

	@GetMapping("/v2/5ed301423400005f0001f1f6")
	Mock getMock();
}
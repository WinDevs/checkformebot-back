package br.com.windevs.checkformebot.clients;

import br.com.windevs.checkformebot.telegram.vo.TelegramSendMessageVO;
import br.com.windevs.checkformebot.telegram.vo.TelegramWebHookVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "telegram", url = "${telegram.url}")
public interface StoreClient {

	@PostMapping("/sendmessage")
	TelegramWebHookVO sendMessage(@SpringQueryMap TelegramSendMessageVO sendMessageVO);

}
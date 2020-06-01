package br.com.windevs.checkformebot.telegram;

import br.com.windevs.checkformebot.telegram.vo.TelegramWebHookVO;
import io.micrometer.core.annotation.Timed;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CommonsLog
@RestController
@RequestMapping("/telegram")
public class TelegramWebHookController {

	public static final String TELEGRAM_INCOMING = "telegram-incoming";
	private final AnswerTelegramService answerTelegramService;

	public TelegramWebHookController(AnswerTelegramService answerTelegramService) {
		this.answerTelegramService = answerTelegramService;
	}

	@Timed(TELEGRAM_INCOMING)
	@PostMapping("/incoming")
	public ResponseEntity<List<String>> receiveIncomingMessages(@RequestBody TelegramWebHookVO telegramWebHook) {
		log.info(telegramWebHook.toString());

		answerTelegramService.answerTelegramWithKeyWords(telegramWebHook);

		return ResponseEntity.ok().build();
	}

}

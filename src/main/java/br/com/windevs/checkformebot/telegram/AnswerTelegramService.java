package br.com.windevs.checkformebot.telegram;

import br.com.windevs.checkformebot.clients.StoreClient;
import br.com.windevs.checkformebot.telegram.vo.TelegramSendMessageVO;
import br.com.windevs.checkformebot.telegram.vo.TelegramWebHookVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AnswerTelegramService {

	private final FindKeyWordDataService findKeyWordDataService;
	private final StoreClient storeClient;
	private final String telegramUserName;

	public AnswerTelegramService(final FindKeyWordDataService findKeyWordDataService,
	                             final StoreClient storeClient,
	                             @Value("${telegram.username}") final String telegramUserName
	) {
		this.findKeyWordDataService = findKeyWordDataService;
		this.storeClient = storeClient;
		this.telegramUserName = telegramUserName;
	}

	public void answerTelegramWithKeyWords(TelegramWebHookVO telegramWebHook) {
		TelegramWebHookVO.Message telegramMessage = telegramWebHook.getMessage();

		if (Objects.nonNull(telegramMessage) &&
				Objects.nonNull(telegramMessage.getText()) &&
				telegramMessage.getText().contains(telegramUserName)) {

			final List<String> keyWords = findKeyWordDataService.findKeyWords(telegramMessage);
			keyWords.forEach(keyWord -> storeClient.sendMessage(new TelegramSendMessageVO(telegramWebHook, keyWord)));
		}
	}

}

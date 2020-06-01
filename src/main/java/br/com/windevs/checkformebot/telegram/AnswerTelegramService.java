package br.com.windevs.checkformebot.telegram;

import br.com.windevs.checkformebot.clients.StoreClient;
import br.com.windevs.checkformebot.telegram.vo.TelegramSendMessageVO;
import br.com.windevs.checkformebot.telegram.vo.TelegramWebHookVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AnswerTelegramService {

private static final String FINAL_TEXT = "I think this isn't true, understand why:\n" +
		"https://checkformebot-front.herokuapp.com/" +
		"result/d52666a1-af16-459b-b99c-8ab46b39fdaa";
	private final StoreClient storeClient;
	private final String telegramUserName;

	public AnswerTelegramService(final StoreClient storeClient,
	                             @Value("${telegram.username}") final String telegramUserName
	) {
		this.storeClient = storeClient;
		this.telegramUserName = telegramUserName;
	}

	public void answerTelegramWithKeyWords(TelegramWebHookVO telegramWebHook) {
		TelegramWebHookVO.Message telegramMessage = telegramWebHook.getMessage();

		if (Objects.nonNull(telegramMessage) &&
				Objects.nonNull(telegramMessage.getText()) &&
				telegramMessage.getText().contains(telegramUserName)) {
			storeClient.sendMessage(new TelegramSendMessageVO(telegramWebHook, FINAL_TEXT));
		}
	}

}

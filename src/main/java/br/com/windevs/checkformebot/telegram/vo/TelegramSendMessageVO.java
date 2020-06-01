package br.com.windevs.checkformebot.telegram.vo;

import lombok.Data;

import java.util.Objects;

@Data
public class TelegramSendMessageVO {
	private String chat_id;
	private String text;
	private String reply_to_message_id;

	public TelegramSendMessageVO(final TelegramWebHookVO telegramWebHookVO) {
		TelegramWebHookVO.Message telegramMessage = telegramWebHookVO.getMessage();

		if (telegramMessage != null) {
			this.text = telegramMessage.getText();
			this.reply_to_message_id = telegramMessage.getMessageId();

			if (Objects.nonNull(telegramMessage.getChat())) {
				this.chat_id = telegramMessage.getChat().getId();
			}

			removeMention();
		}
	}

	public TelegramSendMessageVO(final TelegramWebHookVO telegramWebHookVO, final String text) {
		TelegramWebHookVO.Message telegramMessage = telegramWebHookVO.getMessage();

		if (telegramMessage != null) {
			this.text = text;
			this.reply_to_message_id = telegramMessage.getMessageId();

			if (Objects.nonNull(telegramMessage.getChat())) {
				this.chat_id = telegramMessage.getChat().getId();
			}
		}
	}

	private void removeMention() {
		if (!Objects.isNull(this.text)) {
			this.text = this.text
					.replace("@heycheckformebot", "")
					.replace("  ", " ")
					.trim();
		}
	}
}

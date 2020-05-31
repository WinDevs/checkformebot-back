package br.com.windevs.checkformebot.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class TelegramWebHookVO {
	Message message;

	@JsonAlias("update_id")
	private float updateId;

	@Data
	public static class Message {
		From from;
		Chat chat;
		private float date;
		private String text;

		@JsonAlias("message_id")
		private float messageId;
	}

	@Data
	public static class Chat {
		private float id;
		private String username;
		private String type;

		@JsonAlias("first_name")
		private String firstName;
	}

	@Data
	public static class From {
		private float id;
		private String username;

		@JsonAlias("is_bot")
		private boolean isBot;

		@JsonAlias("first_name")
		private String firstName;

		@JsonAlias("language_code")
		private String languageCode;
	}
}

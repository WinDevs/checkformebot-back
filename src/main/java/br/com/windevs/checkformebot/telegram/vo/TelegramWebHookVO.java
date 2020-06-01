package br.com.windevs.checkformebot.telegram.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class TelegramWebHookVO {

	private Message message;

	@JsonAlias("update_id")
	private String updateId;

	@Data
	public static class Message {

		private From from;
		private Chat chat;
		private String date;
		private String text;

		@JsonAlias("message_id")
		private String messageId;

	}

	@Data
	public static class Chat {

		private String id;
		private String username;
		private String type;

		@JsonAlias("first_name")
		private String firstName;

	}

	@Data
	public static class From {

		private String id;
		private String username;

		@JsonAlias("is_bot")
		private boolean isBot;

		@JsonAlias("first_name")
		private String firstName;

		@JsonAlias("language_code")
		private String languageCode;

	}

}

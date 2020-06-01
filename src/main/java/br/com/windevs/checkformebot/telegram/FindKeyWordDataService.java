package br.com.windevs.checkformebot.telegram;

import br.com.windevs.checkformebot.telegram.vo.TelegramWebHookVO;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CommonsLog
@Service
public class FindKeyWordDataService {

	private static final String SEPARATOR_REGEX = "((\n)|( )|(\t)|(\r))";
	private static final String DATE_REGEX = "((\\d{1,4}([\\/|\\-])\\d{1,2}([\\/|\\-])\\d{1,4})|(\\d{1,4}( |,|, )\\w+( |,|, )\\d{1,4}))";
	private static final String URL_REGEX = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&/=]*)";

	private final String telegramUserName;

	public FindKeyWordDataService(@Value("${telegram.username}") final String telegramUserName) {
		this.telegramUserName = telegramUserName;
	}

	private static String urlEncodeString(String value) {
		try {
			return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ex) {
			log.error(ex);
			return "";
		}
	}

	public List<String> findKeyWords(TelegramWebHookVO.Message telegramMessage) {
		final String textReceived = telegramMessage.getText();

		if (!Objects.isNull(textReceived)) {
			final List<String> words = Arrays.asList(textReceived.split(SEPARATOR_REGEX));
			return getKeyWordList(words);
		}

		return new ArrayList<>();
	}

	private List<String> getKeyWordList(List<String> words) {
		final List<String> list = words
				.stream()
				.filter(word -> word.length() > 3 && !word.equals(telegramUserName))
				.distinct()
				.collect(Collectors.toList());

		return list.stream().map(this::getKeyWordData).collect(Collectors.toList());
	}

	private String getKeyWordData(String string) {
		if (urlEncodeString(string).matches(URL_REGEX)) {
			return "url - " + string;
		} else if (string.matches(DATE_REGEX)) {
			return "date - " + string;
		}

		return "text - " + string;
	}

}

package br.com.windevs.checkformebot.restapi;

import br.com.windevs.checkformebot.vo.TelegramWebHookVO;
import io.micrometer.core.annotation.Timed;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CommonsLog
@RestController
@RequestMapping("/telegram")
public class TelegramWebHookController {

    public static final String TELEGRAM_INCOMING = "telegram-incoming";

    @Timed(TELEGRAM_INCOMING)
    @PostMapping("/incoming")
    public void receiveIncomingMessages(@RequestBody TelegramWebHookVO telegramWebHook) {
        log.info(telegramWebHook.toString());
    }
}

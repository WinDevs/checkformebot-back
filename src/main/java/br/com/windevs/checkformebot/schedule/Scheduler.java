package br.com.windevs.checkformebot.schedule;

import br.com.windevs.checkformebot.clients.Mock;
import br.com.windevs.checkformebot.clients.StoreClient;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
@AllArgsConstructor
public class Scheduler {

    private final StoreClient storeClient;
    private final MeterRegistry registry;

    @Scheduled(cron ="${schedule.cron}")
    public void schedule() {
        registry.counter("scheduler").increment();

        final Mock mock = storeClient.getMock();

        log.info("Run schedule " + mock);
    }
}

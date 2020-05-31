package br.com.windevs.checkformebot.restapi;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Controller {

    private final MeterRegistry registry;

    @Timed("api-hello")
    @GetMapping(value = "hello")
    public ResponseEntity<String> hello() {
        registry.counter("metric").increment();
        return ResponseEntity.ok("OK");
    }
}
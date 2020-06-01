package br.com.windevs.checkformebot.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@GetMapping("/result/{uuid}")
	public ResponseEntity<String> getResult(@PathVariable String uuid) {
		return ResponseEntity.ok("{\n" +
				"  \"uuid\": \"" + uuid + "\",\n" +
				"  \"newsTitle\": \"Coronavirus: why Japanese people with covid-19 are feeling compelled to apologize\",\n" +
				"  \"resultChart\": [\n" +
				"    [\n" +
				"      \"Fake News\",\n" +
				"      75\n" +
				"    ],\n" +
				"    [\n" +
				"      \"Actual News\",\n" +
				"      25\n" +
				"    ]\n" +
				"  ],\n" +
				"  \"resultText\": \"This news has a 75% chance of being fake and 25% chance of being from a reliable source. You should research more about this before you share this kind of content.\",\n" +
				"  \"reliableSources\": [\n" +
				"    \"https://www.bbc.com/news/stories-52731624\",\n" +
				"    \"https://www.bbc.com/news/world-us-canada-52771783\",\n" +
				"    \"https://www.bbc.com/news/world-us-canada-52663603\"\n" +
				"  ]\n" +
				"}");
	}
}

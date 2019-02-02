package com.urielpadiglione.crawlerbot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.urielpadiglione.crawlerbot.client.CrawlerClient;
import com.urielpadiglione.crawlerbot.dto.CrawlerResponseDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerbotApplicationTests {

	@Test
	public void contextLoads() {
		/*
		 * RestTemplate restTemplate = new RestTemplate();
		 * HttpEntity<CrawlerResponseDTO> response =
		 * restTemplate.exchange("http://localhost:8080/redditcrawler/api/get/",
		 * HttpMethod.GET, "", CrawlerResponseDTO.class);
		 */
	}

}


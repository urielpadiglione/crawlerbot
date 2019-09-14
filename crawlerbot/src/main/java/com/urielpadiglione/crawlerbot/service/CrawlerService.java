package com.urielpadiglione.crawlerbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.urielpadiglione.crawlerbot.dto.BotOptionsDTO;


@Service
public class CrawlerService {
	Logger logger = LoggerFactory.getLogger(CrawlerService.class);

	public BotOptionsDTO setConfig(BotOptionsDTO botOptionsDTO) {
		botOptionsDTO.setUpvotes(500);
		
		return botOptionsDTO;
	}
}

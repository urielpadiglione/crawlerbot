package com.urielpadiglione.crawlerbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.urielpadiglione.crawlerbot.bot.CrawlerBot;

@SpringBootApplication
public class CrawlerbotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		
		// Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();
        
        try {
            botsApi.registerBot(new CrawlerBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
		SpringApplication.run(CrawlerbotApplication.class, args);
	}

}


package com.urielpadiglione.crawlerbot.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CrawlerBot extends TelegramLongPollingBot{

	@Override
	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getBotUsername() {
		return "redditcrawlerbot";
	}

	@Override
	public String getBotToken() {
		return "794340959:AAE3hJBg-zBb9isXakv9oGx6nYH1xNhnI6E";
	}

}

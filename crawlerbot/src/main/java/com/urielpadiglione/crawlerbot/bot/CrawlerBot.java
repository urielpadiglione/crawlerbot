package com.urielpadiglione.crawlerbot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.urielpadiglione.crawlerbot.client.CrawlerClient;
import com.urielpadiglione.crawlerbot.dto.CrawlerResponseDTO;
import com.urielpadiglione.crawlerbot.dto.SubredditsDTO;

public class CrawlerBot extends TelegramLongPollingBot{
	Logger logger = LoggerFactory.getLogger(CrawlerBot.class);
	
	/* @Autowired */
	CrawlerClient crawlerClient = new CrawlerClient();

	
	@Override
	public void onUpdateReceived(Update update) {
		CrawlerResponseDTO resp = new CrawlerResponseDTO();
	
    try {
		if (update.hasMessage() && update.getMessage().hasText()) {
			String message_text = update.getMessage().getText();
			logger.info("Message received: "+message_text);
			
			if(message_text.equals("/nadaprafazer ") || message_text.equals("/nadaprafazer")) {
				logger.info("Invalid request treated. Sending message to user.");
				execute(this.getMessage("É necessário digitar um ou mais subreddits após o comando. Exemplo: \n"
						+ "/nadaprafazer AskReddit;pics", update));
			}
			else if(message_text.contains("/nadaprafazer")) {
				SubredditsDTO subs = new SubredditsDTO();
				message_text = message_text.replace("/nadaprafazer ", "");
				subs.setSubreddits(message_text);
				resp = crawlerClient.crawlerClient(subs);
	      

	        	if(resp==null) {
	        		execute(this.getMessage("O bot no momento se encontra fora do ar. Tente novamente mais tarde, ou então venha questionar o pai da criança!! @Morthanc", update)); // Sending our message object to user
	        	}
	        	else if(resp.getResponse()!=null && !resp.getResponse().isEmpty()) {	        	
	        		for(String string : resp.getResponse()) {	        		        		
		        		execute(this.getMessage(string, update)); // Sending our message object to user
		        	}	   
	    		
	        	}else {
	        		//logger.info("No posts found. Subreddit sent: "+message_text);
	        		execute(this.getMessage("Não foi encontrado nenhum post com mais de 5000 upvotes neste subreddit ou o subreddit não existe."+"\n\n", update)); // Sending our message object to user
	        	}
		        	
			}else if(message_text.contains("/start")) {
				logger.info("Start message received.");
				execute(this.getMessage("Bem vindo ao Reddit Crawler Bot!! Para usar o bot, escreva os subreddits depois do comando /nadaprafazer. Exemplo:\n"
						+ "/nadaprafazer AskReddit", update));
			}else if(message_text.contains("/")) {
				logger.info("Invalid command.");
				execute(this.getMessage("Comando inválido!! Para usar o bot, escreva os subreddits depois do comando /nadaprafazer. Exemplo:\n"
						+ "/nadaprafazer AskReddit", update)); // Sending our message object to user		
			}else if(message_text!=null) {
				logger.info("No /nadaprafazer detected");
				execute(this.getMessage("Para usar o bot, escreva os subreddits depois do comando /nadaprafazer. Exemplo:\n"
						+ "/nadaprafazer AskReddit", update)); // Sending our message object to user
			}	      
		}  
	}catch (TelegramApiException e) {
		logger.info("TelegramApiException:  "+e);
        e.printStackTrace();
    }catch (Exception e) {
		logger.info("Unkown error:  "+e);
        e.printStackTrace();
    }

			
			
		
	}

	@Override
	public String getBotUsername() {
		return "redditcrawlerbot";
	}

	@Override
	public String getBotToken() {
		return "794340959:AAE3hJBg-zBb9isXakv9oGx6nYH1xNhnI6E";
	}

	public SendMessage getMessage(String mensagem, Update update) {
		long chat_id = update.getMessage().getChatId();	  
		SendMessage message = new SendMessage() // Create a message object object
		        .setChatId(chat_id)
		        .setText(mensagem);
		return message;
	}
}

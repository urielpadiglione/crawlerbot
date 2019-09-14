package com.urielpadiglione.crawlerbot.client;

import java.util.List;

import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.urielpadiglione.crawlerbot.dto.BotOptionsDTO;
import com.urielpadiglione.crawlerbot.dto.CrawlerResponseDTO;
import com.urielpadiglione.crawlerbot.dto.SubredditsDTO;

@Component
public class CrawlerClient {
	Logger logger = LoggerFactory.getLogger(CrawlerClient.class);
	
	/*
	 * @Autowired Environment env;
	 */
	
	public CrawlerResponseDTO crawlerClient(SubredditsDTO tags, BotOptionsDTO botOptionsDTO){
		try {

			CrawlerResponseDTO crawlerResponseDTO = new CrawlerResponseDTO();
			
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<SubredditsDTO> request = new HttpEntity<>(tags);
			
			String subs = tags.getSubreddits().replace(";", "-");//<<<isso foi feito pois chamadas GET não aceitam ; como separador
			
			HttpEntity<CrawlerResponseDTO> response = restTemplate.exchange("http://localhost:8080/redditcrawler/api/get/"+subs+"/"+botOptionsDTO.getUpvotes(),
				    HttpMethod.GET, request, CrawlerResponseDTO.class);
			
			if(response!=null) {
				crawlerResponseDTO = response.getBody();
				return crawlerResponseDTO;
			}else {
				logger.error("NO RESPONSE RECEIVED FROM THE CRAWLER");
				return null;
			}
		
		}catch(RestClientException e) {
			logger.error("Error while sending GET to the Service. Exception: "+e);
			return null;
		}
	}
}

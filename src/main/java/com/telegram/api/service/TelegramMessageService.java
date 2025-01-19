package com.telegram.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.telegram.api.model.TelegramMessageModel;

import reactor.core.publisher.Mono;

/**
 * TelegramMessageService
 */
@Service
public class TelegramMessageService {

    @Value("${external.api.telegram.url}")
    private String telegramUrl;

    @Value("${external.api.telegram.token}")
    private String telegramToken;

    @Value("${external.api.telegram.chat-id}")
    private String telegramChatId;

    public Mono<TelegramMessageModel> getSendMessage(TelegramMessageModel text) {

         WebClient client = WebClient.builder().baseUrl(telegramUrl).build();
                
        return client.get()
                     .uri(uriBuilder -> uriBuilder.path(telegramToken + "/sendMessage")
                     .queryParam("chat_id", telegramChatId)
                     .queryParam("text", text.getText())
                     .build())
                     .retrieve()
                     .bodyToMono(TelegramMessageModel.class);
        }

    
}
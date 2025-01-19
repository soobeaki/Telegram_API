package com.telegram.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.telegram.api.jsonviews.OnGet;
import com.telegram.api.model.TelegramMessageModel;
import com.telegram.api.service.TelegramMessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * TelegramMessageController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/${api-version}/telegram")
@Tag(name = "telegram", description = "telegram Bot API")
public class TelegramMessageController {

    private final TelegramMessageService telegramMessageService;

    @GetMapping("/send")
    @JsonView(OnGet.class)
    @Operation(summary = "Send a message")
    public Mono<TelegramMessageModel> getSendMessage(@Valid TelegramMessageModel text) {

        return telegramMessageService.getSendMessage(text);
    }

}
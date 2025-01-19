package com.telegram.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.telegram.api.jsonviews.OnGet;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * TelegramMessageModel
 */
@Getter
@Setter
@ToString
public class TelegramMessageModel {

    @Schema(description = "메시지", example = "내용 입력")
    @JsonView(OnGet.class)
    @NotBlank
    private String text;
}

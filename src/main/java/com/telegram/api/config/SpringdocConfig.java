package com.telegram.api.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;

/**
 * SpringdocConfig
 */
@Configuration
public class SpringdocConfig {

    @Value("${spring.profiles.active}")
    private String activedProfile;

    @Value("${api-version}")
    private String apiVersion;

    private Info apiInfo() {
        return new Info()
                .title("Telegram Bot REST API")
                .description("Telegram Bot REST API 명세를 제공한다.")
                .version(apiVersion);
    }

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }
    // .addSecuritySchemes("bearer-key",
    // new SecurityScheme().type(Type.HTTP).scheme("bearer").bearerFormat("JWT")))   // JWT bearer Token 추가.
    

    /**
     * Telegram 업무 API 그룹
     * @return
     */
    @Bean
    @Profile(value = "!prod")
    GroupedOpenApi telegram() {
        return GroupedOpenApi.builder()
                .group("telegram")
                .pathsToMatch("/" + apiVersion + "/telegram/**")
                .build();
    }
    
    /**
     * Sample Header
     * @return
     */
    @Bean
    OpenApiCustomizer Header() {
        Parameter userId = new Parameter()
            .in("header")
            .name("name")
            .description("사용자ID")
            .example("500000001")
            .schema(new StringSchema());

        return openApi -> openApi.getPaths().values().forEach(
                operation -> operation
            .addParametersItem(userId)
        );
                
    }
    
}
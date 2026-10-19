package com.example.summarizer.config;

import jakarta.validation.constraints.NotBlank;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "openrouter")
public record OpenRouterProperties(
        @NotBlank String apiKey,
        @NotBlank @DefaultValue("https://openrouter.ai/api/v1") String baseUrl,
        @NotBlank String model,
        @DefaultValue("30s") Duration timeout,
        @DefaultValue("text-summarizer") String appName,
        @DefaultValue("http://localhost:8080") String siteUrl) {
}

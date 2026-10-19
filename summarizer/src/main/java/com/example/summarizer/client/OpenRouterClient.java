package com.example.summarizer.client;

import com.example.summarizer.config.OpenRouterProperties;
import com.example.summarizer.dto.ChatCompletionRequest;
import com.example.summarizer.dto.ChatCompletionResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class OpenRouterClient {

    private final RestClient restClient;
    private final OpenRouterProperties props;

    public OpenRouterClient(RestClient openRouterRestClient, OpenRouterProperties props) {
        this.restClient = openRouterRestClient;
        this.props = props;
    }

    public ChatCompletionResponse chat(ChatCompletionRequest request) {
        var req = new ChatCompletionRequest(
                request.model() != null ? request.model() : props.model(),
                request.messages(), request.temperature(), request.maxTokens());
        try {
            return restClient.post()
                    .uri("/chat/completions")
                    .body(req)
                    .retrieve()
                    .body(ChatCompletionResponse.class);
        } catch (RestClientResponseException e) {
            throw new UpstreamException(e.getStatusCode().value(),
                    "OpenRouter error: " + e.getResponseBodyAsString(), e);
        } catch (ResourceAccessException e) {
            throw new UpstreamException(504, "OpenRouter timeout/unreachable", e);
        }
    }
}

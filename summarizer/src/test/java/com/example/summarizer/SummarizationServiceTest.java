package com.example.summarizer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.summarizer.client.OpenRouterClient;
import com.example.summarizer.dto.ChatCompletionRequest.Message;
import com.example.summarizer.dto.ChatCompletionResponse;
import com.example.summarizer.dto.ChatCompletionResponse.Choice;
import com.example.summarizer.dto.ChatCompletionResponse.Usage;
import com.example.summarizer.dto.SummarizeRequest;
import com.example.summarizer.service.SummarizationService;
import java.util.List;
import org.junit.jupiter.api.Test;

class SummarizationServiceTest {

    @Test
    void returnsTrimmedSummary() {
        var client = mock(OpenRouterClient.class);
        when(client.chat(any())).thenReturn(new ChatCompletionResponse("1", "test-model",
                List.of(new Choice(new Message("assistant", "  short summary \n"), "stop")),
                new Usage(10, 5, 15)));

        var out = new SummarizationService(client).summarize(new SummarizeRequest("long text", null, null));

        assertThat(out.summary()).isEqualTo("short summary");
        assertThat(out.totalTokens()).isEqualTo(15);
    }
}

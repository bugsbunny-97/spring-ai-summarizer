package com.example.summarizer.web;

import com.example.summarizer.dto.SummarizeRequest;
import com.example.summarizer.dto.SummarizeResponse;
import com.example.summarizer.service.SummarizationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/summaries")
public class SummarizeController {

    private final SummarizationService service;

    public SummarizeController(SummarizationService service) {
        this.service = service;
    }

    @PostMapping
    public SummarizeResponse summarize(@Valid @RequestBody SummarizeRequest request) {
        return service.summarize(request);
    }
}

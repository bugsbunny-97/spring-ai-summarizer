package com.example.summarizer.dto;

public record SummarizeResponse(String summary, String model, int totalTokens) {}

package com.example.summarizer.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SummarizeRequest(
        @NotBlank @Size(max = 50_000) String text,
        Style style,
        @Min(20) @Max(500) Integer maxWords) {

    public enum Style { PARAGRAPH, BULLETS, ONE_LINE }

    public Style styleOrDefault() { return style == null ? Style.PARAGRAPH : style; }
    public int maxWordsOrDefault() { return maxWords == null ? 100 : maxWords; }
}

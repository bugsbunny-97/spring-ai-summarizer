# Text Summarizer (Spring Boot + OpenRouter)

    export OPENROUTER_API_KEY=sk-or-...
    export OPENROUTER_MODEL=openai/gpt-4o-mini   # optional
    mvn spring-boot:run

    curl -X POST localhost:8080/api/v1/summaries \
      -H 'Content-Type: application/json' \
      -d '{"text":"<your long text>","style":"BULLETS","maxWords":80}'

package com.appsound.screensound.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class QueryChatGPT {

    public static String obtainInfo(String text) {
        OpenAiService service = new OpenAiService(System.getenv("OPENAI_APIKEY"));

        CompletionRequest request = CompletionRequest.builder()
                .prompt("ask me about the artist: " + text)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var response = service.createCompletion(request);
        return response.getChoices().get(0).getText();
    }
}


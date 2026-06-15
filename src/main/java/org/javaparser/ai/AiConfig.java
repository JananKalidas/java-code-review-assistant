package org.javaparser.ai;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public GoogleAiGeminiChatModel googleAiGeminiChatModel(
            @Value("${gemini.api.key}")
            String apiKey
    ){
        return GoogleAiGeminiChatModel.builder().
                apiKey(apiKey).modelName("gemini-2.5-flash")
                .build();
    }
}

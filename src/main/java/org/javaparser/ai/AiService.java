package org.javaparser.ai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import lombok.RequiredArgsConstructor;
import org.javaparser.models.AiFinding;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiService {
    private final GoogleAiGeminiChatModel geminiChatModel;
    private final ObjectMapper objectMapper;

    public List<AiFinding> reviewCode(String code){
        String prompt = """
                You are a senior Java code reviewer.
                
                      Review only the code that is explicitly provided.
                
                      Do not infer or assume:
                      - repositories
                      - databases
                      - services
                      - dependency injection
                      - imports
                      - classes not shown
                      - framework annotations
                
                      Ignore compilation issues caused by missing context.
                
                      Focus only on:
                      - maintainability
                      - readability
                      - design
                      - complexity
                      - code smells
                      - security concerns
                
                      If no meaningful findings exist,
                      return an empty JSON array.
                
                      Format:
                
                      [
                        {
                          "severity": "HIGH",
                          "category": "Maintainability",
                          "message": "Too many parameters"
                        }
                      ]
                
                      Rules:
                      - Return maximum 5 findings.
                      - Return JSON only.
                      - No markdown.
                      - No explanations.
                      - No code fences.
                
                      Code:
                
                      %s
                """.formatted(code);
        try{
            String response = geminiChatModel.chat(prompt);
            List<AiFinding> aiFindingList =
                    objectMapper.readValue(
                            response,
                            new TypeReference<List<AiFinding>>() {
                            }
                    );
            return aiFindingList;
        }catch (Exception e){
            throw new RuntimeException(" Failed to process Ai review", e);
        }
    }
}

package org.javaparser.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.javaparser.ai.AiService;
import org.javaparser.dto.AiReviewResponse;
import org.javaparser.dto.ReviewRequest;
import org.javaparser.dto.ReviewResponse;
import org.javaparser.dto.StaticReviewResponse;
import org.javaparser.models.AiFinding;
import org.javaparser.review.ReviewService;
import org.javaparser.staticreview.StaticReviewService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(("/review"))
@RequiredArgsConstructor
public class CodeReviewController {


    private final StaticReviewService staticReviewService;

    private final AiService aiService;

    private final ReviewService reviewService;

    @PostMapping("/static")
    public StaticReviewResponse staticCodeReview(
            @Valid @RequestBody ReviewRequest request){
        return staticReviewService.analyzeCode(request);
    }

    @PostMapping("/ai")
    public AiReviewResponse aiCodeReview(
            @Valid @RequestBody ReviewRequest request
    ){
        List<AiFinding> aiResponse =  aiService.reviewCode(
                request.code()
        );
        return new AiReviewResponse(aiResponse);
    }

    @PostMapping
    public ReviewResponse codeReview(
            @Valid @RequestBody ReviewRequest request
    ){
        return reviewService.review(request);
    }

}

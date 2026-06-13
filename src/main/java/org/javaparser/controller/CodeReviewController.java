package org.javaparser.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.javaparser.dto.ReviewRequest;
import org.javaparser.dto.ReviewResponse;
import org.javaparser.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(("/review"))
@RequiredArgsConstructor
public class CodeReviewController {


    private final ReviewService reviewService;

    @PostMapping
    public ReviewResponse codeReview(
            @Valid @RequestBody ReviewRequest request){
        return reviewService.analyzeCode(request);
    }

}

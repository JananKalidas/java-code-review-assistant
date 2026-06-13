package org.javaparser.service;

import com.github.javaparser.JavaParser;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import lombok.RequiredArgsConstructor;
import org.javaparser.dto.ReviewRequest;
import org.javaparser.dto.ReviewResponse;
import org.javaparser.reviewengine.ReviewEngine;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewEngine reviewEngine;

    public ReviewService(
            ReviewEngine reviewEngine) {

        this.reviewEngine = reviewEngine;
    }

    public ReviewResponse analyzeCode(
            ReviewRequest request
    ){
        String code = request.Code();
        CompilationUnit cu = StaticJavaParser.parse(code);
        return new ReviewResponse(reviewEngine.review(cu));
    }
}

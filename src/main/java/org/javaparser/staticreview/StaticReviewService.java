package org.javaparser.staticreview;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.javaparser.dto.ReviewRequest;
import org.javaparser.dto.StaticReviewResponse;
import org.springframework.stereotype.Service;

@Service
public class StaticReviewService {

    private final StaticReviewEngine staticReviewEngine;

    public StaticReviewService(
            StaticReviewEngine staticReviewEngine) {

        this.staticReviewEngine = staticReviewEngine;
    }

    public StaticReviewResponse analyzeCode(
            ReviewRequest request
    ){
        String code = request.code();
        CompilationUnit cu = StaticJavaParser.parse(code);
        return new StaticReviewResponse(staticReviewEngine.review(cu));
    }
}

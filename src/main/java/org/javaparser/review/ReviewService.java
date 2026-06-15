package org.javaparser.review;

import lombok.RequiredArgsConstructor;
import org.javaparser.ai.AiService;
import org.javaparser.dto.AiReviewResponse;
import org.javaparser.dto.ReviewRequest;
import org.javaparser.dto.ReviewResponse;
import org.javaparser.dto.StaticReviewResponse;
import org.javaparser.models.AiFinding;
import org.javaparser.staticreview.StaticReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final StaticReviewService staticReviewService;
    private final AiService aiService;

    public ReviewResponse review(
            ReviewRequest request
    ){
        StaticReviewResponse staticReview =  staticReviewService.analyzeCode(request);
        List<AiFinding> aiFindings = aiService.reviewCode(request.code());
        AiReviewResponse aiReview = new AiReviewResponse(aiFindings);

        return new ReviewResponse(staticReview.findings(),
                aiReview.findings());
    }
}

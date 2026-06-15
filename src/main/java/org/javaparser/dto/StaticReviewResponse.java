package org.javaparser.dto;

import org.javaparser.models.StaticFindings;

import java.util.List;

public record StaticReviewResponse(
        List<StaticFindings> findings
) {
}

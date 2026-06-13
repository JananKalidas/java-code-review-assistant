package org.javaparser.dto;

import org.javaparser.models.Findings;

import java.util.List;

public record ReviewResponse(
        List<Findings> findings
) {
}

package org.javaparser.dto;

import org.javaparser.models.AiFinding;

import java.util.List;

public record AiReviewResponse (
        List<AiFinding> findings
){};
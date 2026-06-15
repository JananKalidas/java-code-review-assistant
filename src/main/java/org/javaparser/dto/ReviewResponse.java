package org.javaparser.dto;

import org.javaparser.models.AiFinding;
import org.javaparser.models.StaticFindings;

import java.util.List;

public record ReviewResponse (
        List<StaticFindings> staticFindings,
        List<AiFinding> aiFindings
){}

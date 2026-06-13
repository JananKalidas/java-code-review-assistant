package org.javaparser.dto;

import jakarta.validation.constraints.NotBlank;

public record ReviewRequest (
    @NotBlank
    String Code){}



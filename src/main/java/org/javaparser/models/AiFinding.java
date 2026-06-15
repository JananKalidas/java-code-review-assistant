package org.javaparser.models;

public record AiFinding(
        String severity,
        String category,
        String message
) {
}

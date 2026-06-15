package org.javaparser.utili;

import org.javaparser.models.Severity;

public class SeverityUtil {

    public static Severity determineSeverity(
            int actualValue,
            int min_threshold,
            int max_threshold
    ){
        if(actualValue > min_threshold && actualValue <= max_threshold) return Severity.WARNING;
        else if(actualValue > max_threshold) return Severity.CRITICAL;
        else return Severity.INFO;
    }
}

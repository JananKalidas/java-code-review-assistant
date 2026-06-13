package org.javaparser.reviewengine;

import com.github.javaparser.ast.CompilationUnit;
import lombok.RequiredArgsConstructor;
import org.javaparser.analyzer.Analyzer;
import org.javaparser.models.Findings;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewEngine {

    private final List<Analyzer> analyzers;

    public List<Findings> review(CompilationUnit cu) {

        List<Findings> findings = new ArrayList<>();

        for (Analyzer analyzer : analyzers) {
            findings.addAll(
                    analyzer.analyze(cu)
            );

        }
        return findings;
    }
}

package org.javaparser.staticreview;

import com.github.javaparser.ast.CompilationUnit;
import lombok.RequiredArgsConstructor;
import org.javaparser.analyzer.Analyzer;
import org.javaparser.models.StaticFindings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StaticReviewEngine {

    private final List<Analyzer> analyzers;

    public List<StaticFindings> review(CompilationUnit cu) {

        List<StaticFindings> findings = new ArrayList<>();

        for (Analyzer analyzer : analyzers) {
            findings.addAll(
                    analyzer.analyze(cu)
            );

        }
        return findings;
    }
}

package org.javaparser.engine;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.javaparser.analyzer.Analyzer;
import org.javaparser.models.Findings;
import org.javaparser.models.Rule;
import org.javaparser.models.Severity;
import org.javaparser.reviewengine.ReviewEngine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewEngineTests {

    @Mock
    private Analyzer analyzer1;

    @Mock
    private Analyzer analyzer2;

    @Test
    void shouldAggregateFindingsFromAllAnalyzers() {


        Findings finding1 =
                new Findings(
                        Rule.TOO_MANY_LINES,
                        Severity.WARNING,
                        "Long method"
                );

        Findings finding2 =
                new Findings(
                        Rule.COMPLEX_METHOD,
                        Severity.CRITICIAL,
                        "High complexity"
                );

        when(analyzer1.analyze(any()))
                .thenReturn(List.of(finding1));

        when(analyzer2.analyze(any()))
                .thenReturn(List.of(finding2));

        ReviewEngine engine =
                new ReviewEngine(
                        List.of(analyzer1, analyzer2)
                );

        CompilationUnit cu =
                StaticJavaParser.parse(
                        "public class Test {}");

        List<Findings> findings =
                engine.review(cu);

        assertEquals(2, findings.size());
    }

}

package org.javaparser.analyzer;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.javaparser.models.Findings;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ComplexityAnalyzerTests {

    private final ComplexityAnalyzer analyzer =
            new ComplexityAnalyzer();

    @Test
    void shouldDetectHighComplexityMethod() {

        String code = """
            public class Test {

                public void process() {

                    if(true) {}

                    if(true) {}

                    if(true) {}

                    if(true) {}

                    if(true) {}

                    if(true) {}

                    if(true) {}

                    if(true) {}

                    if(true) {}

                    if(true) {}

                    if(true) {}
                }
            }
            """;

        CompilationUnit cu =
                StaticJavaParser.parse(code);

        List<Findings> findings =
                analyzer.analyze(cu);

        assertFalse(findings.isEmpty());
    }
}

package org.javaparser.analyzer;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.javaparser.models.Findings;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class LineAnalyzerTest {

    private final LineAnalyzer analyzer =
            new LineAnalyzer();

    private String generateLongMethod() {

        StringBuilder builder =
                new StringBuilder();

        builder.append(
                "public class Test { public void process() {");

        for (int i = 0; i < 40; i++) {
            builder.append("System.out.println(\"x\");");
        }

        builder.append("}}");

        return builder.toString();
    }

    @Test
    void shouldDetectLongMethod() {

        CompilationUnit cu =
                StaticJavaParser.parse(
                        generateLongMethod());

        List<Findings> findings =
                analyzer.analyze(cu);

        assertFalse(findings.isEmpty());
    }
}

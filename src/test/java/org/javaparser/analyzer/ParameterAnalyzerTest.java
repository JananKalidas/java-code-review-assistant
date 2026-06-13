package org.javaparser.analyzer;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.javaparser.models.Findings;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterAnalyzerTest {

    private final ParameterAnalyzer analyzer =
            new ParameterAnalyzer();

    @Test
    void moreThanFiveParameters() {
        String code = """
                public class EmployeeService {

                    public void createEmployee(
                            String a,
                            String b,
                            String c,
                            String d,
                            String e,
                            String f) {
                    }
                }
                """;
        CompilationUnit cu =
                StaticJavaParser.parse(code);

        List<Findings> findings =
                analyzer.analyze(cu);

        assertEquals(1, findings.size());
    }

    @Test
    void parameterCountIsWithinLimit() {

        String code = """
                public class EmployeeService {

                    public void createEmployee(
                            String a,
                            String b,
                            String c) {
                    }
                }
                """;

        CompilationUnit cu =
                StaticJavaParser.parse(code);

        List<Findings> findings =
                analyzer.analyze(cu);

        assertTrue(findings.isEmpty());
    }

}

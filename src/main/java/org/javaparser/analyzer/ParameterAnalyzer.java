package org.javaparser.analyzer;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.javaparser.models.Findings;
import org.javaparser.models.Rule;
import org.javaparser.models.Severity;
import org.javaparser.utili.SeverityUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParameterAnalyzer implements Analyzer {

    private static final int WARN_THRESHOLD = 5;
    private static final int CRITICAL_THRESHOLD = 7;
    public List<Findings> analyze(CompilationUnit compliationUnit){
        List<Findings> findings = new ArrayList<>();

        compliationUnit.findAll(
                MethodDeclaration.class
        ).forEach( method -> {
            int parameterCount = method.getParameters().size();
            Severity severity = SeverityUtil.determineSeverity(parameterCount, WARN_THRESHOLD, CRITICAL_THRESHOLD);
            if(severity != Severity.INFO){
                findings.add(
                        new Findings( Rule.TOO_MANY_PARAMETERS,
                                severity,
                                String.format(
                                        "Method %s exceeds the recommended line count (%d lines)", method.getNameAsString(), parameterCount
                                ))
                );
            }
        });

        return findings;
    }
}

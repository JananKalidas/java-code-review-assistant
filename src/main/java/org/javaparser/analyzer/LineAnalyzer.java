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
public class LineAnalyzer implements Analyzer {

    private static final int WARN_THRESHOLD = 31;
    private static final int MAX_THRESHOLD = 50;

    public List<Findings> analyze(CompilationUnit compliationUnit){
        List<Findings> findings = new ArrayList<>();

        compliationUnit.findAll(
                MethodDeclaration.class
        ).forEach( method -> {
            method.getRange().ifPresent( range -> {
                int lineCount = range.end.line - range.begin.line + 1;
                Severity severity = SeverityUtil.determineSeverity(lineCount, WARN_THRESHOLD, MAX_THRESHOLD);
                if(severity != Severity.INFO){
                    findings.add(
                            new Findings(Rule.TOO_MANY_LINES,
                                    severity,
                                    String.format(
                                            "Method %s exceeds the recommended line count (%d lines)", method.getNameAsString(), lineCount
                                    ))
                    );
                }
            }
                    
            );
        });

        return findings;
    }
}

package org.javaparser.analyzer;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.*;
import org.javaparser.models.Findings;
import org.javaparser.models.Rule;
import org.javaparser.models.Severity;
import org.javaparser.utili.SeverityUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComplexityAnalyzer implements Analyzer{

    private static final int WARN_THRESHOLD = 10;
    private static final int CRITICAL_THRESHOLD = 12;

    public List<Findings> analyze(CompilationUnit compliationUnit) {
        List<Findings> findings =  new ArrayList<>();
        compliationUnit.findAll(MethodDeclaration.class).
                forEach( method -> {
                            int complexity = 1;
                            complexity += method.findAll(IfStmt.class).size();
                            complexity += method.findAll(ForStmt.class).size();
                            complexity += method.findAll(ForEachStmt.class).size();
                            complexity += method.findAll(WhileStmt.class).size();
                            complexity += method.findAll(CatchClause.class).size();
                            Severity severity = SeverityUtil.determineSeverity(complexity, WARN_THRESHOLD, CRITICAL_THRESHOLD);
                            if(severity != Severity.INFO){
                                findings.add(
                                        new Findings( Rule.COMPLEX_METHOD,
                                                severity,
                                                String.format(
                                                        "Method %s exceeds the recommended line count (%d lines)", method.getNameAsString(), complexity
                                                ))
                                );
                            }
                        }
                        );
        return findings;
    }
}

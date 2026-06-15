package org.javaparser.analyzer;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.*;
import org.javaparser.models.StaticFindings;
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

    public List<StaticFindings> analyze(CompilationUnit compliationUnit) {
        List<StaticFindings> findings =  new ArrayList<>();
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
                                        new StaticFindings( Rule.COMPLEX_METHOD,
                                                severity,
                                                String.format(
                                                        "Method %s complexity is more than expected (%d complexity)", method.getNameAsString(), complexity
                                                ))
                                );
                            }
                        }
                        );
        return findings;
    }
}

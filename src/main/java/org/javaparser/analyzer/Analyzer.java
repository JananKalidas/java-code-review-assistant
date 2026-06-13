package org.javaparser.analyzer;

import com.github.javaparser.ast.CompilationUnit;
import org.javaparser.models.Findings;

import java.util.List;

public interface Analyzer {

    public List<Findings> analyze(
            CompilationUnit compliationUnit);

}

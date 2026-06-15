package org.javaparser.analyzer;

import com.github.javaparser.ast.CompilationUnit;
import org.javaparser.models.StaticFindings;

import java.util.List;

public interface Analyzer {

    public List<StaticFindings> analyze(
            CompilationUnit compliationUnit);

}

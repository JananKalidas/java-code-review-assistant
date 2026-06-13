package org.javaparser.playground;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.io.FileNotFoundException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String code = """
                public class UserService {

                    private String name;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }
                """;

        //For entire String
        CompilationUnit cu = StaticJavaParser.parse(code);
        //System.out.println(cu);

        //for classes
        cu.findAll(
                ClassOrInterfaceDeclaration.class
        ).forEach(cls -> System.out.println("Class: "+ cls.getName()));

        // for methods
        cu.findAll(
                MethodDeclaration.class)
                .forEach(md ->{
                    System.out.println("Method " + md.getName());
                    System.out.println("Return Type " + md.getType());
                    System.out.println("Parameters: "+ md.getParameters().size());
                        }

                );
        // for fields
        cu.findAll(
                FieldDeclaration.class
        ).forEach(
                field -> System.out.println("Fields: "+ field)
        );

        File employeeFile = new File("src/main/java/org/javaparser/code/Employee.java");
        CompilationUnit cuEmp = StaticJavaParser.parse(employeeFile);
        /*
        cuEmp.findAll(MethodDeclaration.class).forEach(
                emp -> {
                    if(emp.getParameters().size() > 5) {
                        System.out.println("Warnings:");
                    }else{
                        System.out.println("OK:");
                    }
                    System.out.println("Method "+ emp.getName()+" has "+ emp.getParameters().size()+ " parameters");
                }
        );
        */
        System.out.println("Method Analysis: ");
//        List<Analyzer> analyzers = Arrays.asList(
//                new ParameterAnalyzer(), new LineAnalyzer(), new ComplexityAnalyzer()
//                );
//        ReviewEngine engine =
//                new ReviewEngine(analyzers);
//        List<Findings> findings =
//                engine.review(cuEmp);
//
//        findings.forEach(
//                finding -> {
//                    System.out.println(
//                            "[" + finding.severity() + "]" + finding.rule() +"\n"+ finding.message()
//                    );
//                }
//        );
    }
}
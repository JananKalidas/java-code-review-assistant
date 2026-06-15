package org.javaparser.models;


public record StaticFindings(
    Rule rule,
    Severity severity,
    String message
){};
//    public Findings(String rule, String message){
//        this.rule = rule;
//        this.message = message;
//    }
//
//    public String message(){
//        return  message;
//    }
//
//    public String rule(){
//        return rule;
//    }



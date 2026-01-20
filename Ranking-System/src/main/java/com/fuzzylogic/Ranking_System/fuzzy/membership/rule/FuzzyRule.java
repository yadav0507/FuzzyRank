package com.fuzzylogic.Ranking_System.fuzzy.membership.rule;

import java.util.Map;

public class FuzzyRule {

    private String cgpa, skill, experience, communication;
    private String output;

    public FuzzyRule(String cgpa, String skill, String experience,
                     String communication, String output) {
        this.cgpa = cgpa;
        this.skill = skill;
        this.experience = experience;
        this.communication = communication;
        this.output = output;
    }

    public double evaluate(Map<String, Double> c,
                           Map<String, Double> s,
                           Map<String, Double> e,
                           Map<String, Double> com) {
        return Math.min(
                Math.min(c.getOrDefault(cgpa,0.0), s.getOrDefault(skill, 0.0)),
                Math.min(e.getOrDefault(experience,0.0), com.getOrDefault(communication,0.0))
        );
    }

    public String getOutput() {

        return output;
    }
}


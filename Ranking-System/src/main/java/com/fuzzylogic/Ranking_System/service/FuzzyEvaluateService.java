package com.fuzzylogic.Ranking_System.service;

import com.fuzzylogic.Ranking_System.dto.CandidateRequestDTO;
import com.fuzzylogic.Ranking_System.dto.CandidateResponseDTO;
import com.fuzzylogic.Ranking_System.fuzzy.membership.TrapezoidalMF;
import com.fuzzylogic.Ranking_System.fuzzy.membership.TriangularMF;
import com.fuzzylogic.Ranking_System.fuzzy.membership.defuzzifier.Defuzzifier;
import com.fuzzylogic.Ranking_System.fuzzy.membership.inference.InferenceEngine;
import com.fuzzylogic.Ranking_System.fuzzy.membership.rule.FuzzyRule;
import com.fuzzylogic.Ranking_System.fuzzy.membership.variable.FuzzyVariable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class FuzzyEvaluateService {

    private final FuzzyVariable cgpa;
    private final FuzzyVariable skill;
    private final FuzzyVariable experience;
    private final FuzzyVariable communication;
    private final List<FuzzyRule> rules;

    public FuzzyEvaluateService() {

        /* ---------- CGPA ---------- */
        cgpa = new FuzzyVariable();
        cgpa.addSet("Low", new TrapezoidalMF(0, 0, 4.5, 5.5));
        cgpa.addSet("Medium", new TriangularMF(4.5, 6.5, 8));
        cgpa.addSet("High", new TrapezoidalMF(7.5, 8.5, 10, 10));

        /* ---------- SKILL ---------- */
        skill = new FuzzyVariable();
        skill.addSet("Low", new TrapezoidalMF(0, 0, 35, 45));
        skill.addSet("Medium", new TriangularMF(40, 60, 80));
        skill.addSet("High", new TrapezoidalMF(75, 85, 100, 100));

        /* ---------- EXPERIENCE ---------- */
        experience = new FuzzyVariable();
        experience.addSet("Low", new TrapezoidalMF(0, 0, 1, 2));
        experience.addSet("Medium", new TriangularMF(1.5, 2.5, 4));
        experience.addSet("High", new TrapezoidalMF(3.5, 4.5, 5, 5));

        /* ---------- COMMUNICATION ---------- */
        communication = new FuzzyVariable();
        communication.addSet("Low", new TrapezoidalMF(0, 0, 3, 4));
        communication.addSet("Medium", new TriangularMF(3.5, 5.5, 7.5));
        communication.addSet("High", new TrapezoidalMF(7, 8.5, 10, 10));

        /* ---------- FUZZY RULE BASE ---------- */
        rules = new ArrayList<>();

        // Very strong profiles
        rules.add(new FuzzyRule("High", "High", "High", "High", "VeryHigh"));
        rules.add(new FuzzyRule("High", "High", "Medium", "High", "VeryHigh"));

        // Strong profiles
        rules.add(new FuzzyRule("High", "Medium", "Low", "High", "High"));
        rules.add(new FuzzyRule("High", "Medium", "Low", "Medium", "High"));

        // Average profiles
        rules.add(new FuzzyRule("Medium", "Medium", "Low", "Medium", "Medium"));
        rules.add(new FuzzyRule("Medium", "High", "Low", "Medium", "High"));

        // Weak profiles
        rules.add(new FuzzyRule("Low", "Medium", "Low", "Low", "Low"));
        rules.add(new FuzzyRule("Low", "Low", "Low", "Low", "Low"));

        // Default fallback rule (important)
        rules.add(new FuzzyRule("Medium", "Medium", "Medium", "Medium", "Medium"));
    }

    public double evaluate(CandidateRequestDTO c) {
        System.out.println("Experience MF: " + experience);
        System.out.println("Communication MF: " + communication);


        Map<String, Double> cgpaVal = cgpa.fuzzify(c.cgpa);
        Map<String, Double> skillVal = skill.fuzzify(c.skill);
        Map<String, Double> expVal = experience.fuzzify(c.experience);
        Map<String, Double> commVal = communication.fuzzify(c.communication);

        InferenceEngine engine = new InferenceEngine();
        Map<String, Double> fuzzyOutput =
                engine.infer(rules, cgpaVal, skillVal, expVal, commVal);

        Defuzzifier defuzzifier = new Defuzzifier();
        return defuzzifier.defuzzify(fuzzyOutput);
    }
    public List<CandidateResponseDTO > rankCandidate(
            List<CandidateRequestDTO> candidate){
        List<CandidateResponseDTO> result = new ArrayList<>();

        // Step 1: Evaluate each candidate

        for(CandidateRequestDTO c: candidate){
            double score = evaluate(c);
            result.add(new CandidateResponseDTO(0, c.name, score));
        }

        // Step 2: Sort by score (descending)
        result.sort((a, b) -> Double.compare(b.finalScore, a.finalScore));

        // Step 3: Assign ranks

        for(int i = 0; i < result.size(); i++){
            result.get(i).rank = i + 1;
        }
        return  result;
    }

}



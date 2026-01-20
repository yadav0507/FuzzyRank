package com.fuzzylogic.Ranking_System.fuzzy.membership.inference;

import com.fuzzylogic.Ranking_System.fuzzy.membership.rule.FuzzyRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InferenceEngine {

    public Map<String, Double> infer(
            List<FuzzyRule> rules,
            Map<String, Double> c,
            Map<String, Double> s,
            Map<String, Double> e,
            Map<String, Double> com) {

        Map<String, Double> output = new HashMap<>();

        for (FuzzyRule rule : rules) {
            double strength = rule.evaluate(c, s, e, com);
            output.merge(rule.getOutput(), strength, Math::max);
        }
        return output;
    }
}


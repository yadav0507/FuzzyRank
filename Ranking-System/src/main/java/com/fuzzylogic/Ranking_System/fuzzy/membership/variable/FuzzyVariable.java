package com.fuzzylogic.Ranking_System.fuzzy.membership.variable;

import com.fuzzylogic.Ranking_System.fuzzy.membership.MembershipFunction;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

public class FuzzyVariable {
    private Map<String, MembershipFunction> sets = new HashMap<>();

    public void addSet(String name, MembershipFunction mf){
        sets.put(name, mf);
    }

    public Map<String, Double> fuzzify(double value){
        Map<String, Double> result = new HashMap<>();
        for(var entry: sets.entrySet()){
            result.put(entry.getKey(), entry.getValue().getMembership(value));
        }
        return result;
    }
}

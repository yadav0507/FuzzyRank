package com.fuzzylogic.Ranking_System.fuzzy.membership.defuzzifier;

import java.util.Map;

public class Defuzzifier {
public double defuzzify(Map<String, Double> fuzzyOutput){
        Map<String, Double> values = Map.of(
                "Low", 25.0,
                "Medium", 50.0,
                "High", 75.0,
                "VeryHigh", 100.0
        );

        double numerator = 0, denominator = 0;

        for(var entry: fuzzyOutput.entrySet()){
            numerator += entry.getValue() * values.get(entry.getKey());
            denominator += entry.getValue();

        }
        if (denominator == 0) {
            return 50.0;
        }



    return  denominator == 0 ? 0 : numerator / denominator;
    }
}

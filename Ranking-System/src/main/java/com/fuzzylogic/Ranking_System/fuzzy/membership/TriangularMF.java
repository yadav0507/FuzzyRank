package com.fuzzylogic.Ranking_System.fuzzy.membership;

public class TriangularMF implements MembershipFunction {
    private double a, b, c;

    public TriangularMF(double a, double b, double c){
        this. a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getMembership(double x){
        if(x <= a || x >= c) return 0;
        else if(x == b) return 1;
        else if(x < b) return (x - a)/ (b - a);
        else return (c - x) / (c - b);
    }
}

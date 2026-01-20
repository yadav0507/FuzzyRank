package com.fuzzylogic.Ranking_System.fuzzy.membership;

public class TrapezoidalMF implements MembershipFunction {

    private final double a, b, c, d;

    public TrapezoidalMF(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double getMembership(double x) {

        // Outside
        if (x < a || x > d) {
            return 0.0;
        }

        // Plateau (FULL membership)
        if (x >= b && x <= c) {
            return 1.0;
        }

        // Rising edge
        if (x >= a && x < b) {
            return (x - a) / (b - a);
        }

        // Falling edge
        return (d - x) / (d - c);
    }
}

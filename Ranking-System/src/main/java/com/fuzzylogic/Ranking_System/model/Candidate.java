package com.fuzzylogic.Ranking_System.model;

public class Candidate {

    public String name;
    public double cgpa;
    public double skill;
    public double experience;

    public Candidate(String name, double cgpa, double skill, double experience){
        this.name = name;
        this.cgpa = cgpa;
        this.skill = skill;
        this.experience = experience;
    }
}

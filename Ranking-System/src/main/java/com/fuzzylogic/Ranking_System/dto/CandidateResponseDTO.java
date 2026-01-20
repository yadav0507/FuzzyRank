package com.fuzzylogic.Ranking_System.dto;

public class CandidateResponseDTO {
    public int rank;
    public String name;
    public double finalScore;

    public CandidateResponseDTO(int rank, String name, double finalScore){
        this.rank = rank;
        this.name = name;
        this.finalScore = finalScore;
    }
}

package com.fuzzylogic.Ranking_System.controller;

import com.fuzzylogic.Ranking_System.dto.CandidateRequestDTO;
import com.fuzzylogic.Ranking_System.dto.CandidateResponseDTO;
import com.fuzzylogic.Ranking_System.service.FuzzyEvaluateService;
import com.fuzzylogic.Ranking_System.service.FuzzyEvaluateService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
    private final FuzzyEvaluateService service;

    public CandidateController(FuzzyEvaluateService service) {
        this.service = service;
    }

    @PostMapping("/evaluate")
    public CandidateResponseDTO evaluate(@RequestBody CandidateRequestDTO req) {

        double score = service.evaluate(req);

        CandidateResponseDTO res = new CandidateResponseDTO(2,"Himanshu", 3);
        res.name = req.name;
        res.finalScore = score;

        return res;
    }
    @PostMapping("/rank")
    public List<CandidateResponseDTO> rankCandidate(
            @Valid @RequestBody List<CandidateRequestDTO> candidates)
    {
        return service.rankCandidate(candidates);
    }

}


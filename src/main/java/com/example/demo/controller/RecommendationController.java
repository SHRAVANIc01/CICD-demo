package com.example.demo.controller;

import com.example.demo.dto.TrackDTO;
import com.example.demo.service.RecommendationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private static final Logger log = LoggerFactory.getLogger(RecommendationController.class);

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{memberId}")
    public List<TrackDTO> getRecommendations(@PathVariable Long memberId) {
        log.info("Received request for recommendations for memberId={}", memberId);
        List<TrackDTO> recommendations = recommendationService.getRecommendationsForMember(memberId);
        log.debug("Returning {} recommended tracks for memberId={}", recommendations.size(), memberId);
        return recommendations;
    }
}

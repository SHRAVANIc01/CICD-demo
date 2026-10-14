package com.example.demo.service;

import com.example.demo.dto.TrackDTO;
import java.util.List;

public interface RecommendationService {
    List<TrackDTO> getRecommendationsForMember(Long memberId);
}

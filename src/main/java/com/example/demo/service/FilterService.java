package com.example.demo.service;

import com.example.demo.dto.TrackDTO;
import java.time.LocalDate;
import java.util.List;

public interface FilterService {
    List<TrackDTO> filterByGenre(String genreName);
    List<TrackDTO> filterByReleaseDate(LocalDate afterDate);
    List<TrackDTO> filterByPopularity(int minPopularity);
    List<TrackDTO> filterByPrice(double maxPrice);
}

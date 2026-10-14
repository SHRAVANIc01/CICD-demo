package com.example.demo.controller;

import com.example.demo.dto.TrackDTO;
import com.example.demo.service.FilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/filter")
public class FilterController {

    private static final Logger log = LoggerFactory.getLogger(FilterController.class);

    private final FilterService filterService;

    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }
    @GetMapping("/hello")
    public String func() {
    	return "Hello world";
    }
    @GetMapping("/genre")
    public List<TrackDTO> filterByGenre(@RequestParam String genreName) {
        log.info("Received request to filter tracks by genre: {}", genreName);
        List<TrackDTO> results = filterService.filterByGenre(genreName);
        log.debug("Found {} tracks for genre '{}'", results.size(), genreName);
        return results;
    }

    @GetMapping("/release-date")
    public List<TrackDTO> filterByReleaseDate(@RequestParam String afterDate) {
        log.info("Received request to filter tracks released after: {}", afterDate);
        LocalDate date = LocalDate.parse(afterDate);
        List<TrackDTO> results = filterService.filterByReleaseDate(date);
        log.debug("Found {} tracks released after {}", results.size(), afterDate);
        return results;
    }

    @GetMapping("/popularity")
    public List<TrackDTO> filterByPopularity(@RequestParam int minPopularity) {
        log.info("Received request to filter tracks with popularity >= {}", minPopularity);
        List<TrackDTO> results = filterService.filterByPopularity(minPopularity);
        log.debug("Found {} tracks with popularity >= {}", results.size(), minPopularity);
        return results;
    }

    @GetMapping("/price")
    public List<TrackDTO> filterByPrice(@RequestParam double maxPrice) {
        log.info("Received request to filter tracks with price <= {}", maxPrice);
        List<TrackDTO> results = filterService.filterByPrice(maxPrice);
        log.debug("Found {} tracks with price <= {}", results.size(), maxPrice);
        return results;
    }
}

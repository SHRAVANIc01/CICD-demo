package com.example.demo.controller;



import com.example.demo.dto.TrackIndexRequest;
import com.example.demo.service.SearchIntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SearchIntegrationController {

    private static final Logger log = LoggerFactory.getLogger(SearchIntegrationController.class);

    private final SearchIntegrationService integrationService;

    public SearchIntegrationController(SearchIntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    /**
     * Endpoint called by Upload/Download service to index a new track.
     * @param request TrackIndexRequest containing track title, artist, genre, uploadedBy
     */
    @PostMapping("/search-integration/index/track")
    public void indexTrack(@RequestBody TrackIndexRequest request) {
        log.info("Received request to index track '{}'", request.getTitle());

        try {
            integrationService.indexTrack(request);
            log.info("Track '{}' indexed successfully", request.getTitle());
        } catch (Exception e) {
            log.error("Failed to index track '{}': {}", request.getTitle(), e.getMessage());
            throw e; // rethrow or handle as per your error strategy
        }
    }
}


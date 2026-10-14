package com.example.demo.controller;

import com.example.demo.dto.ArtistDTO;
//import com.example.demo.dto.GenreDTO;
import com.example.demo.dto.MemberSelectionDTO;
import com.example.demo.service.SearchIntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//8087/catalogue/artist
@RestController
@RequestMapping("/api/search-member-integration")
public class SearchMemberIntegrationController {

    private static final Logger log = LoggerFactory.getLogger(SearchMemberIntegrationController.class);

    private final SearchIntegrationService integrationService;

    public SearchMemberIntegrationController(SearchIntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    /**
     * POST http://localhost:8081/api/search-member-integration/save
     */
    @PostMapping("/save")
    public ResponseEntity<Void> saveSelection(@RequestBody MemberSelectionDTO selection) {
//        log.info("Received selection for memberId={} artistId={}", selection.getMemberId(), selection.getArtistId());
        integrationService.saveSelection(selection);
        return ResponseEntity.ok().build();
    }

    /**
     * POST http://localhost:8081/api/search-member-integration/artist
     */
    @PostMapping("/artist")
    public ResponseEntity<Void> saveArtist(@RequestBody ArtistDTO artistDTO) {
        log.info("Received artist: id={} name={}", artistDTO.getId(), artistDTO.getName());
        integrationService.indexArtist(artistDTO);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/artist/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        log.info("Deleting artist with id={}", id);
        integrationService.deleteArtist(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
    /**
     * POST http://localhost:8081/api/search-member-integration/genre
     */
//    @PostMapping("/genre")
//    public ResponseEntity<Void> saveGenre(@RequestBody GenreDTO genreDTO) {
//        log.info("Received genre: id={} name={}", genreDTO.getId(), genreDTO.getName());
//        integrationService.saveGenre(genreDTO);
//        return ResponseEntity.ok().build();
//    }
}

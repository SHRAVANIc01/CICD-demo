package com.example.demo.controller;

import com.example.demo.dto.TrackDTO;
import com.example.demo.dto.ArtistDTO;
import com.example.demo.dto.AlbumDTO;
import com.example.demo.dto.GenreDTO;
import com.example.demo.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private static final Logger log = LoggerFactory.getLogger(SearchController.class);

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/tracks")
    public List<TrackDTO> searchTracks(@RequestParam String keyword) {
        log.info("Searching tracks with keyword='{}'", keyword);
        List<TrackDTO> results = searchService.searchTracks(keyword);
        log.debug("Found {} tracks for keyword='{}'", results.size(), keyword);
        return results;
    }

    @GetMapping("/artists")
    public List<ArtistDTO> searchArtists(@RequestParam String keyword) {
        log.info("Searching artists with keyword='{}'", keyword);
        List<ArtistDTO> results = searchService.searchArtists(keyword);
        log.debug("Found {} artists for keyword='{}'", results.size(), keyword);
        return results;
    }

    @GetMapping("/albums")
    public List<AlbumDTO> searchAlbums(@RequestParam String keyword) {
        log.info("Searching albums with keyword='{}'", keyword);
        List<AlbumDTO> results = searchService.searchAlbums(keyword);
        log.debug("Found {} albums for keyword='{}'", results.size(), keyword);
        return results;
    }

    @GetMapping("/genres")
    public List<GenreDTO> searchGenres(@RequestParam String keyword) {
        log.info("Searching genres with keyword='{}'", keyword);
        List<GenreDTO> results = searchService.searchGenres(keyword);
        log.debug("Found {} genres for keyword='{}'", results.size(), keyword);
        return results;
    }
}

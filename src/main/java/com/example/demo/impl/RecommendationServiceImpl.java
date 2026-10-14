package com.example.demo.impl;

import com.example.demo.dto.TrackDTO;
import com.example.demo.entity.Track;
import com.example.demo.entity.Preference;
import com.example.demo.repository.TrackRepository;
import com.example.demo.repository.PreferenceRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final PreferenceRepository preferenceRepo;
    private final TrackRepository trackRepo;

    public RecommendationServiceImpl(PreferenceRepository preferenceRepo, TrackRepository trackRepo) {
        this.preferenceRepo = preferenceRepo;
        this.trackRepo = trackRepo;
    }

    @Override
    public List<TrackDTO> getRecommendationsForMember(Long memberId) {
        Preference pref = preferenceRepo.findByMember_Id(memberId);

        // If no preferences found, return empty list
        if (pref == null) {
            return Collections.emptyList();
        }

        // Get tracks based on preferred artists and genres
        List<Track> tracksByGenres = trackRepo.findByGenreIn(pref.getPreferredGenres());
        List<Track> tracksByArtists = trackRepo.findByArtistIn(pref.getPreferredArtists());

        tracksByGenres.addAll(tracksByArtists);

        // Convert Track entities to DTOs including Base64 music data
        return tracksByGenres.stream()
                .map(track -> new TrackDTO(
                        track.getId(),
                        track.getTitle(),
                        track.getDurationInSeconds(),
                        track.getPrice(),
                        track.getPopularity(),
                        track.getAlbum() != null ? track.getAlbum().getId() : null,
                        track.getAlbum() != null ? track.getAlbum().getTitle() : null,
                        track.getArtist() != null ? track.getArtist().getId() : null,
                        track.getArtist() != null ? track.getArtist().getName() : null,
                        track.getGenre() != null ? track.getGenre().getId() : null,
                        track.getGenre() != null ? track.getGenre().getName() : null,
                        track.getMusicDataBase64() // <-- Include Base64 music data
                ))
                .collect(Collectors.toList());
    }
}
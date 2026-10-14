package com.example.demo.impl;

import com.example.demo.dto.TrackDTO;
import com.example.demo.entity.Track;
import com.example.demo.repository.TrackRepository;
import com.example.demo.service.FilterService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterServiceImpl implements FilterService {

    private final TrackRepository trackRepo;

    public FilterServiceImpl(TrackRepository trackRepo) {
        this.trackRepo = trackRepo;
    }

    @Override
    public List<TrackDTO> filterByGenre(String genreName) {
        return trackRepo.findByGenreNameIgnoreCase(genreName)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrackDTO> filterByReleaseDate(LocalDate afterDate) {
        return trackRepo.findByReleaseDateBetween(afterDate, LocalDate.now())
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrackDTO> filterByPopularity(int minPopularity) {
        return trackRepo.findByPopularityGreaterThanEqual(minPopularity)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrackDTO> filterByPrice(double maxPrice) {
        return trackRepo.findByPriceBetween(0, maxPrice)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ---------------- Helper method to convert Track entity to TrackDTO ----------------
    private TrackDTO toDTO(Track track) {
        return new TrackDTO(
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
                track.getMusicDataBase64() // <-- Include Base64 music data here
        );
    }
}
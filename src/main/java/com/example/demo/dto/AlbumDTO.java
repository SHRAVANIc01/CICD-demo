package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

public class AlbumDTO {

    private Long id;
    private String title;
    private LocalDate releaseDate;

    // Minimal artist info
    private Long artistId;
    private String artistName;

    // Tracks inside this album
    private List<TrackDTO> tracks;

    // Constructors
    public AlbumDTO() {}

    public AlbumDTO(Long id, String title, LocalDate releaseDate, Long artistId, String artistName, List<TrackDTO> tracks) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.artistId = artistId;
        this.artistName = artistName;
        this.tracks = tracks;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }

    public List<TrackDTO> getTracks() { return tracks; }
    public void setTracks(List<TrackDTO> tracks) { this.tracks = tracks; }
}

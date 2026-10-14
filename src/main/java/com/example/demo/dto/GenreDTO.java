package com.example.demo.dto;

import java.util.List;

public class GenreDTO {

    private Long id;
    private String name;

    // List of tracks in this genre
    private List<TrackDTO> tracks;

    // Constructors
    public GenreDTO() {}

    public GenreDTO(Long id, String name, List<TrackDTO> tracks) {
        this.id = id;
        this.name = name;
        this.tracks = tracks;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<TrackDTO> getTracks() { return tracks; }
    public void setTracks(List<TrackDTO> tracks) { this.tracks = tracks; }
}

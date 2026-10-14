package com.example.demo.dto;

public class TrackIndexRequest {
    private Long trackId;
    private String title;
    private String artist;
    private String genre;
    private Long uploadedBy;

    // Just Base64 string
    private String musicDataBase64;

    // ---------------- Getters & Setters ----------------
    public Long getTrackId() { return trackId; }
    public void setTrackId(Long trackId) { this.trackId = trackId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Long getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(Long uploadedBy) { this.uploadedBy = uploadedBy; }

    public String getMusicDataBase64() { return musicDataBase64; }
    public void setMusicDataBase64(String musicDataBase64) { this.musicDataBase64 = musicDataBase64; }
}

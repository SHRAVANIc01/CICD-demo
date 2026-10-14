package com.example.demo.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "duration_in_seconds")
    private int durationInSeconds;

    private double price;
    private int popularity; // ranking metric


    // ---------------- Music Base64 Data ----------------
    @Lob
    @Column(name = "music_data", columnDefinition = "LONGTEXT")
    private String musicDataBase64;


    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonBackReference  // Prevent recursion when serializing Track → Album → Track
    private Album album;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    @JsonBackReference  // Prevent recursion when serializing Track → Artist → Track
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @JsonBackReference  // Prevent recursion when serializing Track → Genre → Track
    private Genre genre;

    // ---------------- Getters & Setters ----------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getDurationInSeconds() { return durationInSeconds; }
    public void setDurationInSeconds(int durationInSeconds) { this.durationInSeconds = durationInSeconds; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getPopularity() { return popularity; }
    public void setPopularity(int popularity) { this.popularity = popularity; }


    public String getMusicDataBase64() { return musicDataBase64; }
    public void setMusicDataBase64(String musicDataBase64) { this.musicDataBase64 = musicDataBase64; }


    public Album getAlbum() { return album; }
    public void setAlbum(Album album) { this.album = album; }

    public Artist getArtist() { return artist; }
    public void setArtist(Artist artist) { this.artist = artist; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }
}

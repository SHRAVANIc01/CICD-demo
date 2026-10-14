package com.example.demo.dto;

import java.util.List;

public class ArtistDTO {

    private Long id;
    private String name;
    private String biography;
    private String socialLinks;
    private String discography; // ✅ New field

    // Optional: Include albums and tracks for full detail
    private List<AlbumDTO> albums;
    private List<TrackDTO> tracks;

    // Constructors
    public ArtistDTO() {}

    public ArtistDTO(Long id, String name, String biography, String socialLinks,
                     String discography, List<AlbumDTO> albums, List<TrackDTO> tracks) {
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.socialLinks = socialLinks;
        this.discography = discography;
        this.albums = albums;
        this.tracks = tracks;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }

    public String getSocialLinks() { return socialLinks; }
    public void setSocialLinks(String socialLinks) { this.socialLinks = socialLinks; }

    public String getDiscography() { return discography; }
    public void setDiscography(String discography) { this.discography = discography; }

    public List<AlbumDTO> getAlbums() { return albums; }
    public void setAlbums(List<AlbumDTO> albums) { this.albums = albums; }

    public List<TrackDTO> getTracks() { return tracks; }
    public void setTracks(List<TrackDTO> tracks) { this.tracks = tracks; }
}

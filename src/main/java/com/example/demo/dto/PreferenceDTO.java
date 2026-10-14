package com.example.demo.dto;

import java.util.List;

public class PreferenceDTO {

    private Long id;

    // Optional: memberId instead of full Member object
    private Long memberId;

    // Lists of IDs for preferred genres and artists
    private List<Long> preferredGenreIds;
    private List<Long> preferredArtistIds;

    // Constructors
    public PreferenceDTO() {}

    public PreferenceDTO(Long id, Long memberId, List<Long> preferredGenreIds, List<Long> preferredArtistIds) {
        this.id = id;
        this.memberId = memberId;
        this.preferredGenreIds = preferredGenreIds;
        this.preferredArtistIds = preferredArtistIds;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public List<Long> getPreferredGenreIds() { return preferredGenreIds; }
    public void setPreferredGenreIds(List<Long> preferredGenreIds) { this.preferredGenreIds = preferredGenreIds; }

    public List<Long> getPreferredArtistIds() { return preferredArtistIds; }
    public void setPreferredArtistIds(List<Long> preferredArtistIds) { this.preferredArtistIds = preferredArtistIds; }
}

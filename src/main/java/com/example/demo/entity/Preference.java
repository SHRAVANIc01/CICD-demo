package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "preferences")
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation back to Member
    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    @JsonBackReference  // Prevent recursion when serializing Member → Preference → Member
    private Member member;

    // Preferred genres (up to 10)
    @ManyToMany
    @JoinTable(
            name = "preference_genres",
            joinColumns = @JoinColumn(name = "preference_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> preferredGenres;

    // Preferred artists (up to 10)
    @ManyToMany
    @JoinTable(
            name = "preference_artists",
            joinColumns = @JoinColumn(name = "preference_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> preferredArtists;

    // Constructors
    public Preference() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public List<Genre> getPreferredGenres() { return preferredGenres; }
    public void setPreferredGenres(List<Genre> preferredGenres) { this.preferredGenres = preferredGenres; }

    public List<Artist> getPreferredArtists() { return preferredArtists; }
    public void setPreferredArtists(List<Artist> preferredArtists) { this.preferredArtists = preferredArtists; }
}

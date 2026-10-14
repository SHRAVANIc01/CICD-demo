package com.example.demo.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "members")
public class Member {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    // One Member has one Preference profile
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference  // Prevent recursion when serializing Member → Preference → Member
    private Preference preference;

    // Constructors
    public Member() {}

    public Member(String username) {
        this.username = username;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Preference getPreference() { return preference; }
    public void setPreference(Preference preference) {
        this.preference = preference;
        if (preference != null) {
            preference.setMember(this);
        }
    }
}

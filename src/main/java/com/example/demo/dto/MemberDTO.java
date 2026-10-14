package com.example.demo.dto;

import java.util.List;

public class MemberDTO {

    private Long id;
    private String username;

    // Include preference details
    private PreferenceDTO preference;

    // Constructors
    public MemberDTO() {}

    public MemberDTO(Long id, String username, PreferenceDTO preference) {
        this.id = id;
        this.username = username;
        this.preference = preference;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public PreferenceDTO getPreference() { return preference; }
    public void setPreference(PreferenceDTO preference) { this.preference = preference; }
}

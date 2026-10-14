
package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.ArtistDTO;
import com.example.demo.dto.MemberSelectionDTO;
import com.example.demo.dto.TrackIndexRequest;

public interface SearchIntegrationService {
    void indexTrack(TrackIndexRequest request);
    void indexArtist(ArtistDTO artistDTO);
    void saveSelection(MemberSelectionDTO selection);
    void deleteArtist( Long id);
}

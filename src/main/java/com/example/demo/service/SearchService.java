package com.example.demo.service;

import com.example.demo.dto.TrackDTO;
import com.example.demo.dto.ArtistDTO;
import com.example.demo.dto.AlbumDTO;
import com.example.demo.dto.GenreDTO;

import java.util.List;

public interface SearchService {
    List<TrackDTO> searchTracks(String keyword);
    List<ArtistDTO> searchArtists(String keyword);
    List<AlbumDTO> searchAlbums(String keyword);
    List<GenreDTO> searchGenres(String keyword);
}

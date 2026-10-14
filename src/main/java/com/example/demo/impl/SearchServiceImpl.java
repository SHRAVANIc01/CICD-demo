package com.example.demo.impl;

import com.example.demo.dto.TrackDTO;
import com.example.demo.dto.ArtistDTO;
import com.example.demo.dto.AlbumDTO;
import com.example.demo.dto.GenreDTO;
import com.example.demo.entity.Track;
import com.example.demo.entity.Artist;
import com.example.demo.entity.Album;
import com.example.demo.entity.Genre;
import com.example.demo.repository.TrackRepository;
import com.example.demo.repository.ArtistRepository;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.service.SearchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    private final TrackRepository trackRepo;
    private final ArtistRepository artistRepo;
    private final AlbumRepository albumRepo;
    private final GenreRepository genreRepo;

    public SearchServiceImpl(
            TrackRepository trackRepo,
            ArtistRepository artistRepo,
            AlbumRepository albumRepo,
            GenreRepository genreRepo) {
        this.trackRepo = trackRepo;
        this.artistRepo = artistRepo;
        this.albumRepo = albumRepo;
        this.genreRepo = genreRepo;
    }

    @Override
    public List<TrackDTO> searchTracks(String keyword) {
        return trackRepo.findByTitleContainingIgnoreCase(keyword)
                        .stream()
                        .map(this::mapToTrackDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public List<ArtistDTO> searchArtists(String keyword) {
        return artistRepo.findByNameContainingIgnoreCase(keyword)
                         .stream()
                         .map(this::mapToArtistDTO)
                         .collect(Collectors.toList());
    }

    @Override
    public List<AlbumDTO> searchAlbums(String keyword) {
        return albumRepo.findByTitleContainingIgnoreCase(keyword)
                        .stream()
                        .map(this::mapToAlbumDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public List<GenreDTO> searchGenres(String keyword) {
        return genreRepo.findByNameContainingIgnoreCase(keyword)
                        .stream()
                        .map(this::mapToGenreDTO)
                        .collect(Collectors.toList());
    }

    // ---------- Mappers ----------

    private TrackDTO mapToTrackDTO(Track track) {
        TrackDTO dto = new TrackDTO();
        dto.setId(track.getId());
        dto.setTitle(track.getTitle());
        dto.setDurationInSeconds(track.getDurationInSeconds());
        dto.setPrice(track.getPrice());
        dto.setPopularity(track.getPopularity());
        
        if(track.getAlbum() != null) {
            dto.setAlbumId(track.getAlbum().getId());
            dto.setAlbumTitle(track.getAlbum().getTitle());
        }
        
        if(track.getArtist() != null) {
            dto.setArtistId(track.getArtist().getId());
            dto.setArtistName(track.getArtist().getName());
        }
        
        if(track.getGenre() != null) {
            dto.setGenreId(track.getGenre().getId());
            dto.setGenreName(track.getGenre().getName());
        }

        // ---------------- Include Base64 ----------------
        dto.setMusicDataBase64(track.getMusicDataBase64());

        return dto;
    }


    private ArtistDTO mapToArtistDTO(Artist artist) {
        ArtistDTO dto = new ArtistDTO();
        dto.setId(artist.getId());
        dto.setName(artist.getName());
        dto.setBiography(artist.getBiography());
        dto.setSocialLinks(artist.getSocialLinks());

        // Map albums of the artist
        if(artist.getAlbums() != null) {
            dto.setAlbums(
                artist.getAlbums()
                      .stream()
                      .map(this::mapToAlbumDTO)
                      .collect(Collectors.toList())
            );
        }

        // Map tracks of the artist
        if(artist.getTracks() != null) {
            dto.setTracks(
                artist.getTracks()
                      .stream()
                      .map(this::mapToTrackDTO)
                      .collect(Collectors.toList())
            );
        }

        return dto;
    }

    private AlbumDTO mapToAlbumDTO(Album album) {
        AlbumDTO dto = new AlbumDTO();
        dto.setId(album.getId());
        dto.setTitle(album.getTitle());
        dto.setReleaseDate(album.getReleaseDate());
        if(album.getArtist() != null) {
            dto.setArtistId(album.getArtist().getId());
            dto.setArtistName(album.getArtist().getName());
        }

        // Map tracks inside album
        if(album.getTracks() != null) {
            dto.setTracks(album.getTracks()
                               .stream()
                               .map(this::mapToTrackDTO)
                               .collect(Collectors.toList()));
        }
        return dto;
    }

    private GenreDTO mapToGenreDTO(Genre genre) {
        GenreDTO dto = new GenreDTO();
        dto.setId(genre.getId());
        dto.setName(genre.getName());

        // Map tracks inside genre
        if(genre.getTracks() != null) {
            dto.setTracks(
                genre.getTracks()
                     .stream()
                     .map(this::mapToTrackDTO)
                     .collect(Collectors.toList())
            );
        }

        return dto;
    }
}

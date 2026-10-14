package com.example.demo.impl;

import com.example.demo.dto.TrackIndexRequest;
import com.example.demo.dto.ArtistDTO;
import com.example.demo.dto.MemberSelectionDTO;
import com.example.demo.entity.Artist;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Member;
import com.example.demo.entity.Preference;
import com.example.demo.entity.Track;
import com.example.demo.repository.ArtistRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.PreferenceRepository;
import com.example.demo.repository.TrackRepository;
import com.example.demo.service.SearchIntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.Optional;

@Service
public class SearchIntegrationServiceImpl implements SearchIntegrationService {

    private static final Logger log = LoggerFactory.getLogger(SearchIntegrationServiceImpl.class);

    private final TrackRepository trackRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;
    private final MemberRepository memberRepository;
    private final PreferenceRepository preferenceRepository;

    public SearchIntegrationServiceImpl(TrackRepository trackRepository,
                                        ArtistRepository artistRepository,
                                        GenreRepository genreRepository,
                                        MemberRepository memberRepository,
                                        PreferenceRepository preferenceRepository) {
        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
        this.memberRepository = memberRepository;
        this.preferenceRepository = preferenceRepository;
    }

    @Override
    @Transactional
    public void indexTrack(TrackIndexRequest request) {
        log.info("Indexing track from Upload service: {}", request.getTitle());

        // Resolve artist (create if not exists)
        Artist artist = Optional.ofNullable(artistRepository.findByNameIgnoreCase(request.getArtist()))
                .orElseGet(() -> {
                    Artist newArtist = new Artist();
                    newArtist.setName(request.getArtist());
                    return artistRepository.save(newArtist);
                });

        // Resolve genre (create if not exists)
        Genre genre = Optional.ofNullable(genreRepository.findByNameIgnoreCase(request.getGenre()))
                .orElseGet(() -> {
                    Genre newGenre = new Genre();
                    newGenre.setName(request.getGenre());
                    return genreRepository.save(newGenre);
                });
        int popularity = ThreadLocalRandom.current().nextInt(20, 101); // upper bound is exclusive
        int priceSteps = ThreadLocalRandom.current().nextInt(1, 11); // 1 to 10
        double price = priceSteps * 100.0;
        // Create Track entity
        Track track = new Track();
        track.setTitle(request.getTitle());
        track.setArtist(artist);
        track.setGenre(genre);
        track.setPopularity(popularity);
        track.setDurationInSeconds(1);
        track.setPrice(price);
        track.setMusicDataBase64(request.getMusicDataBase64());

        trackRepository.save(track);
        log.debug("Track '{}' indexed with id={}", track.getTitle(), track.getId());
    }

    @Override
    @Transactional
    public void indexArtist(ArtistDTO artistDTO) {
        log.info("Indexing artist from Catalogue service: {}", artistDTO.getName());

        Artist artist;
//
        if (artistDTO.getId() != null) {
            artist = artistRepository.findById(artistDTO.getId())
                    .orElseGet(() -> {
                        Artist newArtist = new Artist();
                        newArtist.setId(artistDTO.getId()); // only set when NEW
                        return newArtist;
                    });
        } else {
            artist = new Artist(); // fallback if no ID provided
        }

        artist.setName(artistDTO.getName());
        artist.setBiography(artistDTO.getBiography());
        artist.setSocialLinks(artistDTO.getSocialLinks());
        // artist.setDiscography(artistDTO.getDiscography());

        Artist saved = artistRepository.save(artist);
        log.debug("Artist '{}' indexed with id={}", saved.getName(), saved.getId());
    }


    @Override
    @Transactional
    public void deleteArtist(Long id) {
        log.info("Deleting artist with id={}", id);

        if (!artistRepository.existsById(id)) {
            throw new IllegalArgumentException("Artist with id=" + id + " not found");
        }

        artistRepository.deleteById(id);
        log.info("Artist with id={} deleted successfully", id);
    }

    @Override
    @Transactional
    public void saveSelection(MemberSelectionDTO selection) {
        log.info("Saving preferences for memberId={}", selection.getMemberId());

        // Fetch the member
        Member member = memberRepository.findById((long)selection.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Member with id=" + selection.getMemberId() + " not found"));

        // Fetch existing preference or create new
        Preference preference = preferenceRepository.findByMember(member)
                .orElseGet(() -> {
                    Preference p = new Preference();
                    p.setMember(member); // set member immediately
                    return p;
                });
        // Handle preferred artists
        List<Artist> artists = selection.getArtistIds() != null
                ? artistRepository.findAllById(selection.getArtistIds())
                : List.of();
        preference.setPreferredArtists(artists);
        log.debug("Linked {} artists to preferences", artists.size());

        // Handle preferred genres
        List<Genre> genres = selection.getGenreIds() != null
                ? genreRepository.findAllById(selection.getGenreIds())
                : List.of();
        preference.setPreferredGenres(genres);
        log.debug("Linked {} genres to preferences", genres.size());

        // Save preference
        preferenceRepository.save(preference);
        log.info("Preferences for memberId={} saved successfully", selection.getMemberId());
    }
}

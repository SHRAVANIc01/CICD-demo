package com.example.demo.repository;


import com.example.demo.entity.Artist;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

    // Search by track title (case-insensitive)
    List<Track> findByTitleContainingIgnoreCase(String keyword);

    // Filter by genre
    List<Track> findByGenreNameIgnoreCase(String genreName);

    // Filter by release date range
    @Query("SELECT t FROM Track t WHERE t.album.releaseDate BETWEEN :startDate AND :endDate")
    List<Track> findByReleaseDateBetween(@Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);

    // Filter by popularity
    List<Track> findByPopularityGreaterThanEqual(int popularity);

    // Filter by price range
    List<Track> findByPriceBetween(double minPrice, double maxPrice);
 // Find all tracks by a list of genres
    List<Track> findByGenreIn(List<Genre> genres);

    // Find all tracks by a list of artists
    List<Track> findByArtistIn(List<Artist> artists);
}

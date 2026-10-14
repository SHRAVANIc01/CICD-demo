package com.example.demo.repository;



import com.example.demo.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findByTitleContainingIgnoreCase(String title);

    List<Album> findByArtistNameIgnoreCase(String artistName);
}

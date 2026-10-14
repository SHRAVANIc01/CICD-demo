package com.example.demo.repository;



import com.example.demo.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
	Artist findByNameIgnoreCase(String name); 
    List<Artist> findByNameContainingIgnoreCase(String name);
}




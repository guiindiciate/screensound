package com.appsound.screensound.repository;

import com.appsound.screensound.model.Artist;
import com.appsound.screensound.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryArtist extends JpaRepository<Artist, Long> {
    Optional<Artist> findByNameContainingIgnoreCase(String name);

    @Query("SELECT s FROM Artist a  JOIN a.songs s WHERE a.name ILIKE %:name%")
    List<Song> searchSongByArtist(String name);
}

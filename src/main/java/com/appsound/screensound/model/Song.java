package com.appsound.screensound.model;

import jakarta.persistence.*;

@Entity
@Table (name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @ManyToOne
    private Artist artist;

    public Song(){}
    public Song(String songName) {
        this.title = songName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }


    @Override
    public String toString() {
        return
                "Song='" + title + '\'' +
                ", artist=" + artist.getName();
    }
}

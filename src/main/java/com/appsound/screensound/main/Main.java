package com.appsound.screensound.main;

import com.appsound.screensound.model.Artist;
import com.appsound.screensound.model.ArtistType;
import com.appsound.screensound.model.Song;
import com.appsound.screensound.repository.RepositoryArtist;
import com.appsound.screensound.service.QueryChatGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private final RepositoryArtist repository;
    private Scanner scanner = new Scanner(System.in);

    public Main(RepositoryArtist repository) {
        this.repository = repository;
    }

    public void displayMenu() {
        var option = -1;
        while (option != 9) {
            var menu = """
                    1 - Register artist
                    2 - Register song
                    3 - List song
                    4 - Find a song by artist
                    5 - Search data from an artist
                                        
                    9 - Exit           
                    """;

            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    registerArtist();
                    break;
                case 2:
                    registerSong();
                    break;
                case 3:
                    listSong();
                    break;
                case 4:
                    findSongByArtist();
                    break;
                case 5:
                    searchDataFromArtist();
                    break;
                case 9:
                    System.out.println("** Ending application **");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void registerArtist() {
        var newRegister = "Y";

        while (newRegister.equalsIgnoreCase("y")) {


            System.out.println("Inform the artist's name: ");
            var name = scanner.nextLine();

            System.out.println("Inform the type of this artist: (SOLO, DUO, BAND)");
            var type = scanner.nextLine();

            ArtistType artistType = ArtistType.valueOf(type.toUpperCase());
            Artist artist = new Artist(name, artistType);
            repository.save(artist);
            System.out.println("Do want register another artist? (Y/N)");
            newRegister = scanner.nextLine();
        }
    }

    private void registerSong() {

        System.out.println("Inform the artist's name for the song registration: ");
        var name = scanner.nextLine();
        Optional<Artist> artist = repository.findByNameContainingIgnoreCase(name);

        if (artist.isPresent()) {
            System.out.println("Enter title of the song");
            var songName = scanner.nextLine();
            Song song = new Song(songName);
            song.setArtist(artist.get());
            artist.get().getSongs().add(song);
            repository.save(artist.get());
        } else {
            System.out.println("Artist not found!");
        }
    }

    private void listSong() {

        List<Artist> artists = repository.findAll();
        artists.forEach(a -> a.getSongs().forEach(System.out::println));
    }

    private void findSongByArtist() {
        System.out.println("Search song from what artist? ");
        var name = scanner.nextLine();
        List<Song> songs = repository.searchSongByArtist(name);
        songs.forEach(System.out::println);
    }

    private void searchDataFromArtist() {
        System.out.println("Search data about which artist");
        var name = scanner.nextLine();
        var response = QueryChatGPT.obtainInfo(name);
        System.out.println(response.trim());
    }
}

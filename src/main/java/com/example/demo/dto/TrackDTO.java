package com.example.demo.dto;

public class TrackDTO {
    private Long id;
    private String title;
    private int durationInSeconds;
    private double price;
    private int popularity;

    private Long albumId;
    private String albumTitle;

    private Long artistId;
    private String artistName;

    private Long genreId;
    private String genreName;


    // Base64 music data
    private String musicDataBase64;


    // Constructors
    public TrackDTO() {}

    public TrackDTO(Long id, String title, int durationInSeconds, double price, int popularity,
                    Long albumId, String albumTitle,
                    Long artistId, String artistName,
                    Long genreId, String genreName,
                    String musicDataBase64) {

        this.id = id;
        this.title = title;
        this.durationInSeconds = durationInSeconds;
        this.price = price;
        this.popularity = popularity;
        this.albumId = albumId;
        this.albumTitle = albumTitle;
        this.artistId = artistId;
        this.artistName = artistName;
        this.genreId = genreId;
        this.genreName = genreName;

        this.musicDataBase64 = musicDataBase64;

    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getDurationInSeconds() { return durationInSeconds; }
    public void setDurationInSeconds(int durationInSeconds) { this.durationInSeconds = durationInSeconds; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getPopularity() { return popularity; }
    public void setPopularity(int popularity) { this.popularity = popularity; }

    public Long getAlbumId() { return albumId; }
    public void setAlbumId(Long albumId) { this.albumId = albumId; }

    public String getAlbumTitle() { return albumTitle; }
    public void setAlbumTitle(String albumTitle) { this.albumTitle = albumTitle; }

    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }

    public Long getGenreId() { return genreId; }
    public void setGenreId(Long genreId) { this.genreId = genreId; }

    public String getGenreName() { return genreName; }
    public void setGenreName(String genreName) { this.genreName = genreName; }


    public String getMusicDataBase64() { return musicDataBase64; }
    public void setMusicDataBase64(String musicDataBase64) { this.musicDataBase64 = musicDataBase64; }

}

package com.manolitsas.david.util;

import com.manolitsas.david.model.Album;
import com.manolitsas.david.model.Artist;

public class TestUtil {

  public static Artist createNewArtist(
      Long artistId, String name, String primaryGenre, String artistLinkUrl) {
    Artist artist = new Artist();
    artist.setArtistId(artistId);
    artist.setName(name);
    artist.setPrimaryGenreName(primaryGenre);
    artist.setArtistLinkUrl(artistLinkUrl);
    return artist;
  }

  public static Album createNewAlbum(Long albumId, String albumName, String albumUrl) {
    Album album = new Album();
    album.setAlbumId(albumId);
    album.setAlbumName(albumName);
    album.setAlbumUrl(albumUrl);
    return album;
  }
}

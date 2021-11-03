package com.manolitsas.david.services;

import com.manolitsas.david.client.ItunesClient;
import com.manolitsas.david.client.model.ItunesAlbum;
import com.manolitsas.david.client.model.ItunesArtist;
import com.manolitsas.david.client.model.ItunesArtistsAlbums;
import com.manolitsas.david.exceptions.CustomApiException;
import com.manolitsas.david.model.Album;
import com.manolitsas.david.model.Artist;
import com.manolitsas.david.model.ArtistsAlbumsResponse;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** Album service class. */
@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumService {

  private final ItunesClient itunesClient;

  /**
   * Get all albums for an artist.
   *
   * @param artistId the artists itunes id
   * @return the artists details and all albums made by the artist
   */
  public ArtistsAlbumsResponse getArtistAlbums(String artistId) {
    ArtistsAlbumsResponse response = new ArtistsAlbumsResponse();

    try {
      ItunesArtistsAlbums artistsAlbums = itunesClient.findAllAlbumsByArtistId(artistId);

      if (artistsAlbums.getItunesAlbums() != null && artistsAlbums.getItunesArtist() != null) {
        response.setArtist(mapArtist(artistsAlbums.getItunesArtist()));
        response.setAlbums(mapArtistsAlbums(artistsAlbums.getItunesAlbums()));
      }
    } catch (MalformedURLException e) {
      log.error("Itunes URL link is malformed");
      throw CustomApiException.generalTechnicalException(e);
    }

    return response;
  }

  private Artist mapArtist(ItunesArtist itunesArtist) throws MalformedURLException {
    Artist artist = new Artist();
    artist.setArtistId(itunesArtist.getArtistId());
    artist.setName(itunesArtist.getArtistName());
    artist.setPrimaryGenreName(itunesArtist.getPrimaryGenreName());
    artist.setArtistLinkUrl(itunesArtist.getArtistLinkUrl());
    return artist;
  }

  private List<Album> mapArtistsAlbums(List<ItunesAlbum> itunesAlbums)
      throws MalformedURLException {
    List<Album> albums = new ArrayList<>();
    for (ItunesAlbum itunesAlbum : itunesAlbums) {
      Album album = new Album();
      album.setAlbumId(itunesAlbum.getCollectionId());
      album.setAlbumName(itunesAlbum.getCollectionName());
      album.setAlbumUrl(itunesAlbum.getCollectionViewUrl());
      albums.add(album);
    }

    return albums;
  }
}

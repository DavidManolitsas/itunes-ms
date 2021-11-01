package com.manolitsas.david.services;

import com.manolitsas.david.itunes.ItunesClient;
import com.manolitsas.david.itunes.model.ItunesAlbum;
import com.manolitsas.david.itunes.model.ItunesArtist;
import com.manolitsas.david.itunes.model.ItunesArtistsAlbums;
import com.manolitsas.david.web.exceptions.CustomApiException;
import com.manolitsas.david.web.model.Album;
import com.manolitsas.david.web.model.Artist;
import com.manolitsas.david.web.model.ArtistsAlbumsResponse;
import java.net.MalformedURLException;
import java.net.URL;
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
    return Artist.builder()
        .artistId(itunesArtist.getArtistId())
        .name(itunesArtist.getArtistName())
        .primaryGenre(itunesArtist.getPrimaryGenreName())
        .artistUrl(new URL(itunesArtist.getArtistLinkUrl()))
        .build();
  }

  private List<Album> mapArtistsAlbums(List<ItunesAlbum> itunesAlbums)
      throws MalformedURLException {
    List<Album> albums = new ArrayList<>();
    for (ItunesAlbum album : itunesAlbums) {
      albums.add(
          Album.builder()
              .albumId(album.getCollectionId())
              .albumName(album.getCollectionName())
              .albumUrl(new URL(album.getCollectionViewUrl()))
              .build());
    }

    return albums;
  }
}

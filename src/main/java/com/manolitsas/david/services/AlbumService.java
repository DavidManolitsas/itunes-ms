package com.manolitsas.david.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manolitsas.david.itunes.model.ItunesAlbum;
import com.manolitsas.david.itunes.request.ItunesRequest;
import com.manolitsas.david.itunes.response.ItunesAlbumResponse;
import com.manolitsas.david.web.exceptions.CustomApiException;
import com.manolitsas.david.web.model.Album;
import com.manolitsas.david.web.model.Artist;
import com.manolitsas.david.web.model.ArtistsAlbumsResponse;
import java.io.IOException;
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

  private final ItunesRequest request;
  private final ObjectMapper mapper;

  /**
   * Get all albums for an artist.
   *
   * @param artistId the artists itunes id
   * @return the artists details and all albums made by the artist
   */
  public ArtistsAlbumsResponse getArtistAlbums(String artistId) {
    ArtistsAlbumsResponse response = new ArtistsAlbumsResponse();

    try {
      // build itunes api URI request
      URL urlRequest =
          request.buildRequest("https", "/lookup", String.format("id=%s&entity=album", artistId));

      log.info("Sending request to iTunes API [Request={}]", urlRequest);

      ItunesAlbumResponse itunesResponse = mapper.readValue(urlRequest, ItunesAlbumResponse.class);
      List<ItunesAlbum> itunesAlbums = itunesResponse.getResults();

      if (itunesResponse.getResultCount() > 0) {
        // get the artists details
        ItunesAlbum artistDetails = itunesAlbums.get(0);
        response.setArtist(mapArtist(artistDetails));
        // remove the artists details from itunes albums list
        itunesAlbums.remove(0);

        log.info("{} albums found for {}", itunesAlbums.size(), response.getArtist().getName());
        response.setAlbums(mapArtistsAlbums(itunesAlbums));
      } else {
        log.info("No albums found for artist ID {}", artistId);
      }
    } catch (IOException e) {
      throw CustomApiException.generalTechnicalException(e);
    }

    return response;
  }

  private Artist mapArtist(ItunesAlbum artistDetails) throws MalformedURLException {
    return Artist.builder()
        .artistId(artistDetails.getArtistId())
        .name(artistDetails.getArtistName())
        .primaryGenre(artistDetails.getPrimaryGenreName())
        .artistUrl(new URL(artistDetails.getArtistLinkUrl()))
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

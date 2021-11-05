package com.manolitsas.david.module;

import static com.manolitsas.david.constant.Constants.ALBUM_ENTITY;

import com.manolitsas.david.client.ItunesFeignClient;
import com.manolitsas.david.client.model.ItunesAlbumResponse;
import com.manolitsas.david.exception.CustomApiException;
import com.manolitsas.david.mapper.ItunesAlbumMapper;
import com.manolitsas.david.model.ArtistsAlbumsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/** Album service class. */
@Slf4j
@Component
@RequiredArgsConstructor
public class AlbumModule {

  private final ItunesFeignClient itunesClient;
  private final ItunesAlbumMapper mapper;

  /**
   * Get all albums for an artist.
   *
   * @param artistId the artists itunes id
   * @return the artists details and all albums made by the artist
   */
  public ArtistsAlbumsResponse getArtistAlbums(String artistId) {
    ArtistsAlbumsResponse response = new ArtistsAlbumsResponse();

    ItunesAlbumResponse itunesResponse =
        itunesClient.findAllAlbumsByArtistId(artistId, ALBUM_ENTITY);

    if (!itunesResponse.getResults().isEmpty()) {
      response.setArtist(mapper.albumArtistDetailsToArtist(itunesResponse.getResults().get(0)));
      // remove the artists details from list of albums
      itunesResponse.getResults().remove(0);
      response.setAlbums(mapper.itunesAlbumListToAlbumList(itunesResponse.getResults()));
      return response;
    } else {
      log.info("No albums found for artist id [ArtistId={}]", artistId);
      throw CustomApiException.notFoundException(
          String.format("No albums found for artist id: %s", artistId));
    }
  }
}

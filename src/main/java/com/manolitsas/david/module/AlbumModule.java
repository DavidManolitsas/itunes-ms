package com.manolitsas.david.module;

import static com.manolitsas.david.constant.Constants.ALBUM_ENTITY;

import com.manolitsas.david.client.ItunesFeignClient;
import com.manolitsas.david.client.model.ItunesAlbum;
import com.manolitsas.david.client.model.ItunesAlbumResponse;
import com.manolitsas.david.exception.CustomApiException;
import com.manolitsas.david.model.Album;
import com.manolitsas.david.model.Artist;
import com.manolitsas.david.model.ArtistsAlbumsResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/** Album service class. */
@Slf4j
@Component
@RequiredArgsConstructor
public class AlbumModule {

  private final ItunesFeignClient itunesClient;

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
      response.setArtist(mapArtist(itunesResponse.getResults().get(0)));
      // remove the artists details from list of albums
      itunesResponse.getResults().remove(0);
      response.setAlbums(mapArtistsAlbums(itunesResponse.getResults()));
      return response;
    } else {
      log.info("No albums found for artist id [ArtistId={}]", artistId);
      throw CustomApiException.notFoundException(
          String.format("No albums found for artist id: %s", artistId));
    }
  }

  private Artist mapArtist(ItunesAlbum itunesArtistDetails) {
    Artist artist = new Artist();
    artist.setArtistId(itunesArtistDetails.getArtistId());
    artist.setName(itunesArtistDetails.getArtistName());
    artist.setPrimaryGenreName(itunesArtistDetails.getPrimaryGenreName());
    artist.setArtistLinkUrl(itunesArtistDetails.getArtistLinkUrl());
    return artist;
  }

  private List<Album> mapArtistsAlbums(List<ItunesAlbum> itunesAlbums) {
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

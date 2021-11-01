package com.manolitsas.david.itunes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manolitsas.david.itunes.model.ItunesAlbum;
import com.manolitsas.david.itunes.model.ItunesArtist;
import com.manolitsas.david.itunes.model.ItunesArtistsAlbums;
import com.manolitsas.david.itunes.response.ItunesAlbumResponse;
import com.manolitsas.david.itunes.response.ItunesArtistsResponse;
import com.manolitsas.david.web.exceptions.CustomApiException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** Itunes API request. */
@Data
@Builder
@RequiredArgsConstructor
@Slf4j
public class ItunesClient {

  private final String endpoint;
  private final ObjectMapper mapper;

  /**
   * Find all albums by an artists.
   *
   * @param artistId artists id
   * @return an iTunes artist and their albums
   */
  public ItunesArtistsAlbums findAllAlbumsByArtistId(String artistId) {

    ItunesArtistsAlbums artistsAlbums = new ItunesArtistsAlbums();
    try {

      URL httpRequest =
          buildHttpRequest("https", "/lookup", String.format("id=%s&entity=album", artistId));
      log.info("Sending request to iTunes API [Request={}]", httpRequest);

      ItunesAlbumResponse itunesResponse = mapper.readValue(httpRequest, ItunesAlbumResponse.class);
      List<ItunesAlbum> itunesAlbums = itunesResponse.getResults();

      if (itunesResponse.getResultCount() > 0) {
        ItunesAlbum artistDetails = itunesAlbums.get(0);

        ItunesArtist artist =
            ItunesArtist.builder()
                .wrapperType(artistDetails.getWrapperType())
                .artistType(artistDetails.getArtistType())
                .artistName(artistDetails.getArtistName())
                .artistLinkUrl(artistDetails.getArtistLinkUrl())
                .artistId(artistDetails.getArtistId())
                .amgArtistId(artistDetails.getAmgArtistId())
                .primaryGenreName(artistDetails.getPrimaryGenreName())
                .primaryGenreId(artistDetails.getPrimaryGenreId())
                .build();

        artistsAlbums.setItunesArtist(artist);
        // remove the artists details from itunes albums list
        itunesAlbums.remove(0);

        log.info("{} albums found for {}", itunesAlbums.size(), artist.getArtistName());
        artistsAlbums.setItunesAlbums(itunesAlbums);

      } else {
        log.info("No albums found for artist ID {}", artistId);
      }
    } catch (IOException e) {
      throw CustomApiException.generalTechnicalException(e);
    }

    return artistsAlbums;
  }

  /**
   * Find the 5 most relevant iTunes artists based on a given a search term.
   *
   * @param term search term
   * @return a list of iTunes artists
   */
  public List<ItunesArtist> findAllArtistsByTermLimitBy5(String term) {

    try {
      URL httpRequest =
          buildHttpRequest(
              "https",
              "/search",
              String.format("term=%s&media=music&entity=musicArtist&limit=5", term));
      log.info("Sending request to iTunes API [Request={}]", httpRequest);

      ItunesArtistsResponse itunesResponse =
          mapper.readValue(httpRequest, ItunesArtistsResponse.class);

      log.info("{} artists found for search term '{}'", itunesResponse.getResultCount(), term);

      return itunesResponse.getResults();

    } catch (IOException e) {
      throw CustomApiException.generalTechnicalException(e);
    }
  }

  private URL buildHttpRequest(String scheme, String path, String query) {
    try {
      return new URI(scheme, endpoint, path, query, null).toURL();
    } catch (URISyntaxException | MalformedURLException e) {
      throw CustomApiException.generalTechnicalException("Invalid request to iTunes API", e);
    }
  }
}

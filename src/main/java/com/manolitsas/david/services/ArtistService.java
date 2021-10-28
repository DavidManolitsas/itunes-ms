package com.manolitsas.david.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manolitsas.david.itunes.request.ItunesRequest;
import com.manolitsas.david.itunes.response.ItunesArtistsResponse;
import com.manolitsas.david.web.exceptions.CustomApiException;
import com.manolitsas.david.web.model.Artist;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Artist service class.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArtistService {

  private final ItunesRequest request;
  private final ObjectMapper mapper;

  /**
   * Retrieve the 5 most relevant artists based on a search term.
   *
   * @param term search term
   * @return list of artists
   */
  public List<Artist> getArtists(String term) {

    try {

      URL urlRequest =
          request.buildRequest(
              "https",
              "/search",
              String.format("term=%s&media=music&entity=musicArtist&limit=5", term));

      log.info("Sending request to iTunes API [Request={}]", urlRequest);

      ItunesArtistsResponse itunesResponse =
          mapper.readValue(urlRequest, ItunesArtistsResponse.class);

      log.info("{} artists found", itunesResponse.getResultCount());
      return mapItunesResponse(itunesResponse);

    } catch (IOException e) {
      throw CustomApiException.generalTechnicalException(e);
    }
  }

  private List<Artist> mapItunesResponse(ItunesArtistsResponse itunesResponse) {
    return itunesResponse.getResults().stream()
        .map(
            artist -> {
              try {
                return Artist.builder()
                    .artistId(artist.getArtistId())
                    .name(artist.getArtistName())
                    .primaryGenre(artist.getPrimaryGenreName())
                    .artistUrl(new URL(artist.getArtistLinkUrl()))
                    .build();
              } catch (MalformedURLException e) {
                throw CustomApiException.generalTechnicalException(e);
              }
            })
        .collect(Collectors.toList());
  }
}

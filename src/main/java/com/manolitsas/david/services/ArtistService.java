package com.manolitsas.david.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manolitsas.david.itunes.model.ItunesArtist;
import com.manolitsas.david.web.exceptions.CustomApiException;
import com.manolitsas.david.web.model.Artist;
import com.manolitsas.david.web.model.ArtistsResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

  @Value("${itunes.client}")
  private String itunesApi;

  private final ObjectMapper mapper;

  public ArtistService(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  /**
   * Retrieve the 5 most relevant artists based on a search term
   *
   * @param term search term
   * @return list of artists
   */
  public List<Artist> getArtists(String term) {

    try {
      URI uri =
          new URI(
              "https",
              itunesApi,
              "/search",
              String.format("term=%s&media=music&entity=musicArtist&limit=5", term),
              null);
      List<ItunesArtist> artists =
          mapper.readValue(uri.toURL(), ArtistsResponse.class).getResults();

      return artists.stream()
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
    } catch (URISyntaxException | IOException e) {
      throw CustomApiException.generalTechnicalException(e);
    }
  }
}

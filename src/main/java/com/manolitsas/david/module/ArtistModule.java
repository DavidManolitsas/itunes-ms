package com.manolitsas.david.module;

import static com.manolitsas.david.constant.Constants.FIVE_LIMIT;
import static com.manolitsas.david.constant.Constants.MUSIC_ARTIST_ENTITY;
import static com.manolitsas.david.constant.Constants.MUSIC_MEDIA;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manolitsas.david.client.ItunesFeignClient;
import com.manolitsas.david.client.model.ItunesArtist;
import com.manolitsas.david.client.model.ItunesArtistsResponse;
import com.manolitsas.david.model.Artist;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/** Artist service class. */
@Slf4j
@Component
@RequiredArgsConstructor
public class ArtistModule {

  private final ItunesFeignClient itunesClient;
  private final ObjectMapper mapper;

  /**
   * Retrieve the 5 most relevant artists based on a search term.
   *
   * @param term search term
   * @return list of artists
   */
  public List<Artist> getArtists(String term) {
    try {
      String itunesJsonResponse =
          itunesClient.findAllArtistsByTerm(term, MUSIC_MEDIA, MUSIC_ARTIST_ENTITY, FIVE_LIMIT);
      return mapItunesResponse(
          mapper.readValue(itunesJsonResponse, ItunesArtistsResponse.class).getResults());
    } catch (JsonProcessingException e) {
      log.error("unable to map itunes response");
      return null;
    }
  }

  private List<Artist> mapItunesResponse(List<ItunesArtist> artists) {
    return artists.stream()
        .map(
            itunesArtist -> {
              Artist artist = new Artist();
              artist.setArtistId(itunesArtist.getArtistId());
              artist.setName(itunesArtist.getArtistName());
              artist.setPrimaryGenreName(itunesArtist.getPrimaryGenreName());
              artist.setArtistLinkUrl(itunesArtist.getArtistLinkUrl());
              return artist;
            })
        .collect(Collectors.toList());
  }
}

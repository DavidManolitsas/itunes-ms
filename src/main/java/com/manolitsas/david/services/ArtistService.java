package com.manolitsas.david.services;

import com.manolitsas.david.itunes.ItunesClient;
import com.manolitsas.david.itunes.model.ItunesArtist;
import com.manolitsas.david.web.exceptions.CustomApiException;
import com.manolitsas.david.web.model.Artist;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** Artist service class. */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArtistService {

  private final ItunesClient itunesClient;

  /**
   * Retrieve the 5 most relevant artists based on a search term.
   *
   * @param term search term
   * @return list of artists
   */
  public List<Artist> getArtists(String term) {
    return mapItunesResponse(itunesClient.findAllArtistsByTermLimitBy5(term));
  }

  private List<Artist> mapItunesResponse(List<ItunesArtist> artists) {
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
  }
}

package com.manolitsas.david.services;

import com.manolitsas.david.client.ItunesClient;
import com.manolitsas.david.client.model.ItunesArtist;
import com.manolitsas.david.model.Artist;
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

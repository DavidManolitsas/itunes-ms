package com.manolitsas.david.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manolitsas.david.client.model.ItunesAlbum;
import com.manolitsas.david.client.model.ItunesArtist;
import com.manolitsas.david.client.model.ItunesArtistsAlbums;
import com.manolitsas.david.client.model.ItunesAlbumResponse;
import com.manolitsas.david.client.model.ItunesArtistsResponse;
import com.manolitsas.david.exceptions.CustomApiException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** Itunes API request. */
@FeignClient(name = "${itunes-client.name}", url = "${itunes-client.url}")
public interface ItunesClient {

  @GetMapping(value = "/lookup", produces = MediaType.APPLICATION_JSON_VALUE)
  ItunesArtistsResponse findAllArtistsByTermLimitBy5(
          @RequestParam(value="term") String term,
          @RequestParam(value="media") String media,
          @RequestParam(value="entity") String entity,
          @RequestParam(value="limit") String limit);

//  term=%s
//  &media=music
//  &entity=musicArtist
//  &limit=5

  @GetMapping("/search")
  ItunesArtistsAlbums findAllAlbumsByArtistId(@RequestParam(value="id") String artistId, @RequestParam(value="entity") String entity);

//  /**
//   * Find all albums by an artists.
//   *
//   * @param artistId artists id
//   * @return an iTunes artist and their albums
//   */
//  public ItunesArtistsAlbums findAllAlbumsByArtistId(String artistId) {
//
//    ItunesArtistsAlbums artistsAlbums = new ItunesArtistsAlbums();
//    try {
//
//      URL httpRequest =
//          buildHttpRequest("https", "/lookup", String.format("id=%s&entity=album", artistId));
//      log.info("Sending request to iTunes API [Request={}]", httpRequest);
//
//      ItunesAlbumResponse itunesResponse = mapper.readValue(httpRequest, ItunesAlbumResponse.class);
//      List<ItunesAlbum> itunesAlbums = itunesResponse.getResults();
//
//      if (itunesResponse.getResultCount() > 0) {
//        ItunesAlbum artistDetails = itunesAlbums.get(0);
//
//        ItunesArtist artist =
//            ItunesArtist.builder()
//                .wrapperType(artistDetails.getWrapperType())
//                .artistType(artistDetails.getArtistType())
//                .artistName(artistDetails.getArtistName())
//                .artistLinkUrl(artistDetails.getArtistLinkUrl())
//                .artistId(artistDetails.getArtistId())
//                .amgArtistId(artistDetails.getAmgArtistId())
//                .primaryGenreName(artistDetails.getPrimaryGenreName())
//                .primaryGenreId(artistDetails.getPrimaryGenreId())
//                .build();
//
//        artistsAlbums.setItunesArtist(artist);
//        // remove the artists details from itunes albums list
//        itunesAlbums.remove(0);
//
//        log.info(
//            "{} albums found for {} [ArtistId={}]",
//            itunesAlbums.size(),
//            artist.getArtistName(),
//            artistId);
//        artistsAlbums.setItunesAlbums(itunesAlbums);
//
//      } else {
//        log.info("No albums found for artist ID '{}'", artistId);
//      }
//    } catch (IOException e) {
//      throw CustomApiException.generalTechnicalException(e);
//    }
//
//    return artistsAlbums;
//  }
//
//  /**
//   * Find the 5 most relevant iTunes artists based on a given a search term.
//   *
//   * @param term search term
//   * @return a list of iTunes artists
//   */
//  public List<ItunesArtist> findAllArtistsByTermLimitBy5(String term) {
//
//    try {
//      URL httpRequest =
//          buildHttpRequest(
//              "https",
//              "/search",
//              String.format("term=%s&media=music&entity=musicArtist&limit=5", term));
//      log.info("Sending request to iTunes API [Request={}]", httpRequest);
//
//      ItunesArtistsResponse itunesResponse =
//          mapper.readValue(httpRequest, ItunesArtistsResponse.class);
//
//      log.info("{} artists found for search term '{}'", itunesResponse.getResultCount(), term);
//
//      return itunesResponse.getResults();
//
//    } catch (IOException e) {
//      throw CustomApiException.generalTechnicalException(e);
//    }
//  }
//
//  private URL buildHttpRequest(String scheme, String path, String query) {
//    try {
//      return new URI(scheme, endpoint, path, query, null).toURL();
//    } catch (URISyntaxException | MalformedURLException e) {
//      throw CustomApiException.generalTechnicalException("Invalid request to iTunes API", e);
//    }
//  }
}

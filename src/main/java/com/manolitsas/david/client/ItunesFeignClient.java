package com.manolitsas.david.client;

import com.manolitsas.david.configuration.ItunesFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** Itunes API request. */
@FeignClient(
    name = "${itunes-client.name}",
    url = "${itunes-client.url}",
    configuration = ItunesFeignClientConfiguration.class)
public interface ItunesFeignClient {

  @GetMapping(
      value = "/search",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  String findAllArtistsByTerm(
      @RequestParam(value = "term") String term,
      @RequestParam(value = "media") String media,
      @RequestParam(value = "entity") String entity,
      @RequestParam(value = "limit") String limit);

  @GetMapping(
      value = "/lookup",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  String findAllAlbumsByArtistId(
      @RequestParam(value = "id") String artistId, @RequestParam(value = "entity") String entity);
}

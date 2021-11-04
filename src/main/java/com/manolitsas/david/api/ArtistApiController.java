package com.manolitsas.david.api;

import com.manolitsas.david.model.Artist;
import com.manolitsas.david.module.ArtistModule;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itunes")
public class ArtistApiController implements ArtistsApi {

  private final ArtistModule service;

  public ArtistApiController(ArtistModule service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<List<Artist>> getArtists(String term) {
    return ResponseEntity.ok(service.getArtists(term));
  }
}

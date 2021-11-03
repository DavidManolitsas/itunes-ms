package com.manolitsas.david.api;

import com.manolitsas.david.model.Artist;
import com.manolitsas.david.services.ArtistService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/itunes")
public class ArtistApiController implements ArtistsApi {

  private final ArtistService service;

  public ArtistApiController(ArtistService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<List<Artist>> getArtists(String term) {
    return ResponseEntity.ok(service.getArtists(term));
  }
}

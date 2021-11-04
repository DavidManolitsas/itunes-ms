package com.manolitsas.david.api;

import com.manolitsas.david.model.Artist;
import com.manolitsas.david.module.ArtistModule;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Artist API controller endpoints. */
@RestController
@RequiredArgsConstructor
@RequestMapping("/itunes")
public class ArtistApiController implements ArtistsApi {

  private final ArtistModule artistModule;

  @Override
  public ResponseEntity<List<Artist>> getArtists(String term) {
    return ResponseEntity.ok(artistModule.getArtists(term));
  }
}

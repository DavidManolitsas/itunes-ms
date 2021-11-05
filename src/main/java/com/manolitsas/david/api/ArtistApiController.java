package com.manolitsas.david.api;

import com.manolitsas.david.model.Artist;
import com.manolitsas.david.module.ArtistModule;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** Artist API controller endpoints. */
@Controller
@RequiredArgsConstructor
@RequestMapping("/itunes")
public class ArtistApiController implements ArtistsApi {

  private final ArtistModule artistModule;

  @Override
  public ResponseEntity<List<Artist>> getArtists(String term) {
    return ResponseEntity.ok(artistModule.getArtists(term));
  }
}

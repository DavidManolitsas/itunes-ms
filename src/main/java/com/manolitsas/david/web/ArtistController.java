package com.manolitsas.david.web;

import com.manolitsas.david.services.ArtistService;
import com.manolitsas.david.web.model.Artist;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Artist controller class.
 */
@RestController
@RequestMapping("/artists")
public class ArtistController {

  private ArtistService service;

  public ArtistController(ArtistService service) {
    this.service = service;
  }

  @GetMapping("/{term}")
  public List<Artist> getArtists(@PathVariable String term) {
    return service.getArtists(term);
  }
}

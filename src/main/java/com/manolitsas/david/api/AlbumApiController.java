package com.manolitsas.david.api;

import com.manolitsas.david.model.ArtistsAlbumsResponse;
import com.manolitsas.david.module.AlbumModule;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itunes")
public class AlbumApiController implements AlbumsApi {

  private final AlbumModule service;

  public AlbumApiController(AlbumModule service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ArtistsAlbumsResponse> getAlbums(@Valid String artistId) {
    return ResponseEntity.ok(service.getArtistAlbums(artistId));
  }
}

package com.manolitsas.david.api;

import com.manolitsas.david.model.ArtistsAlbumsResponse;
import com.manolitsas.david.services.AlbumService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/itunes")
public class AlbumApiController implements AlbumsApi {

  private final AlbumService service;

  public AlbumApiController(AlbumService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ArtistsAlbumsResponse> getAlbums(@Valid String artistId) {
    return ResponseEntity.ok(service.getArtistAlbums(artistId));
  }
}

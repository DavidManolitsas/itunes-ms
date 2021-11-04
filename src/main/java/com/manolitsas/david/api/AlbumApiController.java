package com.manolitsas.david.api;

import com.manolitsas.david.model.ArtistsAlbumsResponse;
import com.manolitsas.david.module.AlbumModule;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/itunes")
public class AlbumApiController implements AlbumsApi {

  private final AlbumModule albumModule;

  @Override
  public ResponseEntity<ArtistsAlbumsResponse> getAlbums(@Valid String artistId) {
    return ResponseEntity.ok(albumModule.getArtistAlbums(artistId));
  }
}

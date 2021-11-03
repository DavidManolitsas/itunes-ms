package com.manolitsas.david.client.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Itunes artists albums API model. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItunesArtistsAlbums {

  private ItunesArtist itunesArtist;
  private List<ItunesAlbum> itunesAlbums;
}

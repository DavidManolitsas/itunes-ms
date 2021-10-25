package com.manolitsas.david.web.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistsAlbumsResponse {

  private Artist artist;
  private List<Album> albums;
}

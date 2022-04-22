package com.manolitsas.david.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

/** Itunes API artists response. */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItunesArtistsResponse {

  @JsonProperty private int resultCount;
  @JsonProperty private List<ItunesArtist> results;

  public ItunesArtistsResponse(int resultCount) {
    this.resultCount = resultCount;
    this.results = new ArrayList<>();
  }
}

package com.manolitsas.david.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.manolitsas.david.itunes.model.ItunesArtist;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistsResponse {

  @JsonProperty private int resultCount;
  @JsonProperty private List<ItunesArtist> results;

  public ArtistsResponse(int resultCount) {
    this.resultCount = resultCount;
    this.results = new ArrayList<>();
  }
}

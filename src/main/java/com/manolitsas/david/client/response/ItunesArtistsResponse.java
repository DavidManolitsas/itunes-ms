package com.manolitsas.david.client.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.manolitsas.david.client.model.ItunesArtist;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Itunes API artists response. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItunesArtistsResponse {

  @JsonProperty private int resultCount;
  @JsonProperty private List<ItunesArtist> results;

  public ItunesArtistsResponse(int resultCount) {
    this.resultCount = resultCount;
    this.results = new ArrayList<>();
  }
}

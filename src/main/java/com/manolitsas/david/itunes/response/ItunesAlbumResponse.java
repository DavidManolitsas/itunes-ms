package com.manolitsas.david.itunes.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.manolitsas.david.itunes.model.ItunesAlbum;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** iTunes album result class. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItunesAlbumResponse {

  @JsonProperty private int resultCount;
  @JsonProperty private List<ItunesAlbum> results;

  public ItunesAlbumResponse(int resultCount) {
    this.resultCount = resultCount;
    this.results = new ArrayList<>();
  }
}

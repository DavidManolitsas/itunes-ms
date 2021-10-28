package com.manolitsas.david.itunes.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Itunes album API model.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItunesAlbum {
  // artist
  @JsonProperty private String artistType;
  @JsonProperty private String artistLinkUrl;
  @JsonProperty private String amgArtistId;
  @JsonProperty private long primaryGenreId;
  // albums
  @JsonProperty private String wrapperType;
  @JsonProperty private String collectionType;
  @JsonProperty private long artistId;
  @JsonProperty private long collectionId;
  @JsonProperty private String artistName;
  @JsonProperty private String collectionName;
  @JsonProperty private String collectionCensoredName;
  @JsonProperty private String artistViewUrl;
  @JsonProperty private String collectionViewUrl;
  @JsonProperty private String artworkUrl60;
  @JsonProperty private String artworkUrl100;
  @JsonProperty private double collectionPrice;
  @JsonProperty private String collectionExplicitness;
  @JsonProperty private String contentAdvisoryRating;
  @JsonProperty private long trackCount;
  @JsonProperty private String copyright;
  @JsonProperty private String country;
  @JsonProperty private String currency;
  @JsonProperty private String releaseDate;
  @JsonProperty private String primaryGenreName;
}

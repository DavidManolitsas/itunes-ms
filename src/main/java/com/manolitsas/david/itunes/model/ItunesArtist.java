package com.manolitsas.david.itunes.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** iTunes model artist class. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItunesArtist {

    @JsonProperty private String wrapperType;
    @JsonProperty private String artistType;
    @JsonProperty private String artistName;
    @JsonProperty private String artistLinkUrl;
    @JsonProperty private long artistId;
    @JsonProperty private String amgArtistId;
    @JsonProperty private String primaryGenreName;
    @JsonProperty private long primaryGenreId;
}
package com.manolitsas.david.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistsAlbumsResponse {

    private Artist artist;
    private List<Album> albums;

}

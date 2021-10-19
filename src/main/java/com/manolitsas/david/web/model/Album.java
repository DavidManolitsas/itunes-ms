package com.manolitsas.david.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    private Long albumId;
    private String albumName;
    private URL albumUrl;

}

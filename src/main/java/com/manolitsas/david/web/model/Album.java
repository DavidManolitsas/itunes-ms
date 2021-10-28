package com.manolitsas.david.web.model;

import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Album model class.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Album {

  private Long albumId;
  private String albumName;
  private URL albumUrl;
}

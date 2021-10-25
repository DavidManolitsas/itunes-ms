package com.manolitsas.david.web.model;

import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Artist {

  private Long artistId;
  private String name;
  private String primaryGenre;
  private URL artistUrl;
}

package com.manolitsas.david.itunes.request;

import com.manolitsas.david.web.exceptions.CustomApiException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItunesRequest {

  private String endpoint;

  public URL buildRequest(String scheme, String path, String query) {
    try {

      return new URI(scheme, endpoint, path, query, null).toURL();
    } catch (URISyntaxException | MalformedURLException e) {
      throw CustomApiException.generalTechnicalException("Invalid request to iTunes API", e);
    }
  }
}

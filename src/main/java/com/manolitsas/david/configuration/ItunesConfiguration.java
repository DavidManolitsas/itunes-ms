package com.manolitsas.david.configuration;

import com.manolitsas.david.itunes.request.ItunesRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ItunesConfiguration {

  @Value("${itunes.client}")
  private String itunesApi;

  @Bean
  @Primary
  public ItunesRequest itunesRequestClient() {
    return ItunesRequest.builder().endpoint(itunesApi).build();
  }
}

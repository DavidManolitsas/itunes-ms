package com.manolitsas.david.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manolitsas.david.client.ItunesClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/** Configure itunes API client and endpoint. */
@Configuration
public class ItunesConfiguration {

  @Value("${itunes.client}")
  private String itunesApi;

  @Bean
  @Primary
  public ItunesClient itunesRequestClient() {
    return ItunesClient.builder().endpoint(itunesApi).mapper(new ObjectMapper()).build();
  }
}

package com.manolitsas.david.configuration;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Configure itunes API Feign Client. */
@Configuration
public class ItunesFeignClientConfiguration {

  @Bean
  public Decoder feignDecoder() {
    return new JacksonDecoder();
  }
}

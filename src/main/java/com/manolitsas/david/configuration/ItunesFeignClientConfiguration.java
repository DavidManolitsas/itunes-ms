package com.manolitsas.david.configuration;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/** Configure itunes API client and endpoint. */
@Configuration
public class ItunesFeignClientConfiguration {

  @Bean
  RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
        new MappingJackson2HttpMessageConverter();
    mappingJackson2HttpMessageConverter.setSupportedMediaTypes(
        Arrays.asList(
            MediaType.APPLICATION_JSON, MediaType.parseMediaType("text/javascript;charset=utf-8")));
    restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
    return restTemplate;
  }
}

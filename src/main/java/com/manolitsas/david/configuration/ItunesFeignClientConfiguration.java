package com.manolitsas.david.configuration;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Configure itunes API client and endpoint. */
@Configuration
public class ItunesFeignClientConfiguration {

  @Bean
  public Decoder feignDecoder() {
    return new JacksonDecoder();
  }

  //  @Bean
  //  public Decoder feignDecoder() {
  //    HttpMessageConverter jacksonConverter = new
  // MappingJackson2HttpMessageConverter(customObjectMapper());
  //    ObjectFactory<HttpMessageConverters> objectFactory = () -> new
  // HttpMessageConverters(jacksonConverter);
  //    return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
  //  }

  //  @Bean
  //  RestTemplate restTemplate() {
  //    RestTemplate restTemplate = new RestTemplate();
  //    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
  //        new MappingJackson2HttpMessageConverter();
  //    mappingJackson2HttpMessageConverter.setSupportedMediaTypes(
  //        Arrays.asList(
  //            MediaType.APPLICATION_JSON,
  // MediaType.parseMediaType("text/javascript;charset=utf-8")));
  //    restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
  //    return restTemplate;
  //  }

}

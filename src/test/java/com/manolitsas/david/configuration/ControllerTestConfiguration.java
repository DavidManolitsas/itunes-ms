package com.manolitsas.david.configuration;

import com.manolitsas.david.module.AlbumModule;
import com.manolitsas.david.module.ArtistModule;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for testing web slice. This configuration contains:
 *
 * <ul>
 *   <li>Mock instances of all module the controller depends on
 *   <li>Required beans that are not loaded by @WebMvcTest. Either mock or import real beans
 * </ul>
 */
@Configuration
@MockBeans({@MockBean({AlbumModule.class, ArtistModule.class})})
public class ControllerTestConfiguration {}

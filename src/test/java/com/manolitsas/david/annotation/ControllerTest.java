package com.manolitsas.david.annotation;

import com.manolitsas.david.configuration.ControllerTestConfiguration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

/**
 * Meta-annotation (annotation of annotations) to group Spring's annotations for testing web slice.
 */
@WebMvcTest()
@ActiveProfiles("test")
@Import(ControllerTestConfiguration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerTest {}

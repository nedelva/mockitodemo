package org.soapservice.cdi;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.core.io.ResourceLoader;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.net.URL;

/**
 * Bean {@link Factory factory} for producing injectable URLs pointing to the SOAP services WSDL used by
 * the SOAP client wrapper beans.<p>Each producer method throws IllegalStateException in the unlikely event that the
 * WSDL file is not found on the classpath.</p>
 *
 * @see #SOME_SERVICE_V_1_0_WSDL
 * @see WsdlLocation
 */
@Factory
public class WsdlLocationProvider {

    public static final String SOME_SERVICE_V_1_0_WSDL = "wsdls/SomeService_v1.0.wsdl";
    private static final String RESOURCE_COULD_NOT_BE_FOUND = "Resource '%s' could not be found by the loader %s";
    @Inject
    @Named("classpathResourceLoader")
    ResourceLoader resourceLoader;


    @Bean
    @Singleton
    @WsdlLocation("SomeService_v1.0.wsdl")
    public URL provideCodeListWsdlLocation() {
        return resourceLoader.getResource(SOME_SERVICE_V_1_0_WSDL)
                .orElseThrow(() -> new IllegalStateException(
                        String.format(RESOURCE_COULD_NOT_BE_FOUND, SOME_SERVICE_V_1_0_WSDL,
                                resourceLoader.getClass().getSimpleName())));
    }

    // Add more @Bean methods for other SOAP clients
}

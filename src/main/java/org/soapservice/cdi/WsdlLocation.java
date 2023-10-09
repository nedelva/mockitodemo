package org.soapservice.cdi;

import io.micronaut.context.annotation.AliasFor;
import jakarta.inject.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A qualifier annotation to represent the WSDL location. This will be used by the SOAP client wrapper beans to
 * inject the URL to the WSDL.
 *
 * @see WsdlLocationProvider
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Qualifier
public @interface WsdlLocation {
    @AliasFor(annotation = Qualifier.class, member = "value")
    String value() default "";
}


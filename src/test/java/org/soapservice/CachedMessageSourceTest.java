package org.soapservice;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.soapservice.clients.SomeServiceClient;
import org.soapservice.v1.BusinessMethodRequestType;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@MicronautTest
public class CachedMessageSourceTest {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CachedMessageSourceTest.class);
    @Inject
    CachedMessageSource cachedMessageSource;

    @Inject
    SomeServiceClient someServiceClient; //injected by MocksFactory

    @BeforeEach
    void setup() {
        log.debug("Injected service ref: '{}'", someServiceClient);
    }

    /** the behavior of mock object is somehow not verified so this test
     * fails at line 43
     * */
    @Test
    void testCacheableAnnotation() throws ExecutionException {
        String code = "aCode";

        // First invocation with the same parameters should call the method.
        String message = cachedMessageSource.getMessage(code, Locale.ENGLISH);
        assertEquals("Payload for aCode", message, "should return the string set by the MocksFactory");
        Mockito.verify(someServiceClient, Mockito.times(1)).businessMethod(any(BusinessMethodRequestType.class));

        // Second invocation with the same parameters should be cached and not call the method again.
        message = cachedMessageSource.getMessage(code, Locale.ENGLISH);
        Mockito.verify(someServiceClient, Mockito.times(1)).businessMethod(any(BusinessMethodRequestType.class));

        // Change the parameters, and the method should be called again.
        cachedMessageSource.getMessage("otherCode", Locale.FRENCH);
        Mockito.verify(someServiceClient, Mockito.times(2)).businessMethod(any(BusinessMethodRequestType.class));
    }

}

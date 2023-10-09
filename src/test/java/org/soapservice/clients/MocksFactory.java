package org.soapservice.clients;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.client.ServiceHttpClientConfiguration;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.mockito.Mockito;
import org.soapservice.v1.BusinessMethodRequestType;
import org.soapservice.v1.BusinessMethodResponseType;
import org.soapservice.v1.SomeService;

import static org.mockito.ArgumentMatchers.any;

/**
 * <p>Bean factory that is used specifically for creating beans during testing,
 * providing bean definitions that will be included in the application context during tests.</p>
 * <p>The purpose of this class is to define mock and real beans based on a configuration value (useMocks) and replace
 * existing beans with these mock implementations during testing.</p>
 */
@Factory
@Slf4j
class MocksFactory {

    private final boolean useMocks;

    public MocksFactory(@Value("${mocks.enabled:true}") boolean useMocks) {
        this.useMocks = useMocks;
    }

    @Bean
    @Replaces(SomeServiceClient.class)
    public SomeServiceClient someServiceClient(@Named("my-service") ServiceHttpClientConfiguration httpClientConfiguration,
                                               SomeService someService) {
        if (useMocks) {
            log.debug("About to create a mock of SomeServiceClient");
            SomeServiceClient mock = Mockito.mock(SomeServiceClient.class);
            log.debug("created mock {}", mock);
            BusinessMethodResponseType fakeResponse = createFakeResponse("aCode");
            log.debug("Attaching behaviour to '{}'", mock);
            Mockito.when(mock.businessMethod(
                            any(BusinessMethodRequestType.class)))
                    .thenReturn(fakeResponse);
            return mock;
        } else {
            return new SomeServiceDefaultClient(httpClientConfiguration, someService);
        }
    }

    private static BusinessMethodResponseType createFakeResponse(String valueCode) {
        BusinessMethodResponseType response = new BusinessMethodResponseType();
        response.setPayload("Payload for " + valueCode);

        return response;
    }
}

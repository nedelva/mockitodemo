package org.soapservice.clients;

import io.micronaut.http.client.ServiceHttpClientConfiguration;
import jakarta.xml.ws.BindingProvider;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.soapservice.v1.BusinessMethodRequestType;
import org.soapservice.v1.BusinessMethodResponseType;
import org.soapservice.v1.SomeService;
import org.soapservice.v1.SomeServicePort;

@Slf4j
public class SomeServiceDefaultClient implements SomeServiceClient {

    private SomeServicePort port;

    public SomeServiceDefaultClient(ServiceHttpClientConfiguration httpClientConfiguration, SomeService someService) {
        String endpointURL = String.valueOf(httpClientConfiguration.getUrls().get(0));
        log.debug("Using endpoint URL '{}'", endpointURL);

        port = someService.getSomeServicePort();
        ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
    }

    @SneakyThrows
    @Override
    public BusinessMethodResponseType businessMethod(BusinessMethodRequestType requestType) {
        return port.businessMethod(requestType);
    }
}

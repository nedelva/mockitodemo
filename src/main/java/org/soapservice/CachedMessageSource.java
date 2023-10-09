package org.soapservice;

import io.micronaut.cache.annotation.Cacheable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.soapservice.clients.SomeServiceClient;
import org.soapservice.v1.BusinessMethodRequestType;
import org.soapservice.v1.BusinessMethodResponseType;

import java.util.Locale;

@Singleton
public class CachedMessageSource implements MessageSource {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CachedMessageSource.class);
    final SomeServiceClient someServiceClient;

    @Inject
    public CachedMessageSource(SomeServiceClient someServiceClient) {
        this.someServiceClient = someServiceClient;
    }

    @Cacheable(cacheNames = "my-cache")
    @Override
    public String getMessage(String code, Locale inputLocale) {
        log.debug("code={}, inputLocale={}", code, inputLocale);

        BusinessMethodRequestType request = new BusinessMethodRequestType();
        request.setCode(code);
        BusinessMethodResponseType codeValueResponseType = someServiceClient.businessMethod(request);

        return codeValueResponseType.getPayload();
    }
}

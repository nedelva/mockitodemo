package org.soapservice.clients;

import org.soapservice.v1.BusinessMethodRequestType;
import org.soapservice.v1.BusinessMethodResponseType;

public interface SomeServiceClient {
    BusinessMethodResponseType businessMethod(BusinessMethodRequestType requestType);
}

package org.soapservice.v1;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://org.soapservice/SomeService/v1", name = "SomeServicePort")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SomeServicePort {

    @WebMethod(action = "./businessMethod")
    @WebResult(name = "BusinessMethodResponseMsg", targetNamespace = "http://org.soapservice/SomeService/v1", partName = "parameters")
    BusinessMethodResponseType businessMethod(
            @WebParam(partName = "parameters", name = "BusinessMethodRequestMsg", targetNamespace = "http://org.soapservice/SomeService/v1")
            BusinessMethodRequestType parameters
    ) throws Exception;

}

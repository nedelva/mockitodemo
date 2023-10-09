package org.soapservice.v1;

import jakarta.inject.Singleton;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceFeature;
import org.soapservice.cdi.WsdlLocation;

import javax.xml.namespace.QName;
import java.net.URL;

@Singleton
@WebServiceClient(name = "SomeService",
        targetNamespace = "http://org.soapservice/SomeService/v1")
public class SomeService extends Service {

    public static final QName SERVICE = new QName("http://org.soapservice/SomeService/v1", "SomeService");
    public static final QName SomeServicePort = new QName("http://org.soapservice/SomeService/v1", "SomeServicePort");

    public SomeService(@WsdlLocation("SomeService_v1.0.wsdl") URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    /**
     * @return returns SomeServicePort
     */
    @WebEndpoint(name = "SomeServicePort")
    public SomeServicePort getSomeServicePort() {
        return super.getPort(SomeServicePort, SomeServicePort.class);
    }

    /**
     * @param features A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns SomeServicePort
     */
    @WebEndpoint(name = "SomeServicePort")
    public SomeServicePort getSomeServicePort(WebServiceFeature... features) {
        return super.getPort(SomeServicePort, SomeServicePort.class, features);
    }

}
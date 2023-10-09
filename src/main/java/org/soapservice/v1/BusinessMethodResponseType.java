
package org.soapservice.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "businessMethodResponseType", namespace = "http://org.soapservice/SomeService/v1", propOrder = {
        "payload"})
public class BusinessMethodResponseType {
    @XmlElement(name = "payload", namespace = "http://org.soapservice/SomeService/v1", required = true)
    private String payload;


    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
}

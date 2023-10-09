
package org.soapservice.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
public class BusinessMethodRequestType {

    @XmlElement(name = "valueCode", namespace = "http://org.soapservice/someService/v1")
    protected String code;


    public void setCode(String code) {
        this.code = code;
    }
}

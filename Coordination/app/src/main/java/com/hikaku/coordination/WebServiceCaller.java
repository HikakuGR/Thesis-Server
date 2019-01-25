package com.hikaku.coordination;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Map;

/**
 * Created by Hikaku on 2/6/2015.
 */
public class WebServiceCaller {

    public Object CallMethod(WebMethodParameters webMethodParameters) throws Exception {
        String soapAction = webMethodParameters.getNamespace() + webMethodParameters.getMethodName();
        SoapObject request = new SoapObject(webMethodParameters.getNamespace(),
                webMethodParameters.getMethodName());

        if (webMethodParameters.getParameters() != null) {
            for (Map.Entry<String, String> entry : webMethodParameters.getParameters().entrySet()) {
                request.addProperty(entry.getKey(), entry.getValue());
            }
        }

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(
                webMethodParameters.getUrl());
        androidHttpTransport.call(soapAction, envelope);
        Object result = (Object) envelope.getResponse();
        return result;
    }

}

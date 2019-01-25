package com.hikaku.coordination;

import java.util.Map.Entry;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;

public class WebServiceCallerTask extends
		AsyncTask<WebMethodParameters, Integer, Object> {

	@Override
	protected Object doInBackground(WebMethodParameters... params) {
		try 
		{
			WebServiceCaller webServiceCaller = new WebServiceCaller();
			return webServiceCaller.CallMethod(params[0]);
		} 
		catch (Exception ex) 
		{
			return ex;
		}
	}
}

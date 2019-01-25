package com.hikaku.coordination;

import java.util.concurrent.ConcurrentHashMap;

public class WebMethodParameters {
	private String methodName;
	private ConcurrentHashMap<String, String> parameters;
	private String namespace;
	private String url;
	
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public ConcurrentHashMap<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(ConcurrentHashMap<String, String> parameters) {
		this.parameters = parameters;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}

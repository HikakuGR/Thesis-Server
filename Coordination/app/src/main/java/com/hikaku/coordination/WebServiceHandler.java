package com.hikaku.coordination;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.ksoap2.serialization.SoapObject;

import android.location.Location;

public class WebServiceHandler {

    private String Url;
    private String Namespace;

    public WebServiceHandler(String url) {
        this.Url = url;
        this.Namespace = "http://tempuri.org/";
    }

    public String GetVersion() throws Exception {
        return this.CallMethod("GetVersion", null, true).toString();
    }

    public int Login(String username, String password) throws Exception {
        ConcurrentHashMap<String, String> parameters = new ConcurrentHashMap<String, String>();
        parameters.put("username", username);
        parameters.put("password", password);

        Object result = this.CallMethod("Login", parameters, true).toString();
        return Integer.parseInt(result.toString());
    }

    public int Register(String username, String password, String mail) throws Exception {
        ConcurrentHashMap<String, String> parameters = new ConcurrentHashMap<String, String>();
        parameters.put("username", username);
        parameters.put("password", password);
        parameters.put("mail", mail);

        Object result = this.CallMethod("Register", parameters, true).toString();
        return Integer.parseInt(result.toString());

    }

    public Job GetJobByID(Integer ID)throws Exception {
        ConcurrentHashMap<String, String> parameters = new ConcurrentHashMap<String, String>();
        parameters.put("ID",ID.toString());
        Object result = this.CallMethod("GetJobByID", parameters, true);

        SoapObject soapResult = (SoapObject) result;
        return ConvertJob(soapResult);
    }

    private Job ConvertJob(SoapObject soapJob){
        Job job = new Job();
        job.ID = Integer.parseInt(soapJob.getProperty("ID").toString());
        job.description = soapJob.getProperty("description").toString();
        job.latitude = Double.parseDouble(soapJob.getProperty("latitude").toString());
        job.longitude = Double.parseDouble(soapJob.getProperty("longitude").toString());
        job.jobName = soapJob.getProperty("jobName").toString();
        job.completed = Boolean.parseBoolean(soapJob.getProperty("completed").toString());
        job.assigned = Boolean.parseBoolean(soapJob.getProperty("assigned").toString());
        return job;
    }

    public ArrayList<Job> GetJobs() throws Exception {
        ArrayList<Job> jobs = new ArrayList<Job>();
        Object result = this.CallMethod("GetJobs", null, true);
        SoapObject soapResult = (SoapObject) result;

        for (int i = 0; i < soapResult.getPropertyCount(); i++) {
            SoapObject soapJob = (SoapObject) soapResult.getProperty(i);
            jobs.add(ConvertJob(soapJob));
        }

        return jobs;

    }

    public int SendLastKnownLocation(Location LastKnownLocation, Integer ID) throws Exception {
        ConcurrentHashMap<String, String> parameters = new ConcurrentHashMap<String, String>();

        String latitude = String.valueOf(LastKnownLocation.getLatitude());
        String longitude = String.valueOf(LastKnownLocation.getLongitude());
        parameters.put("latitude", latitude);
        parameters.put("longitude", longitude);
        parameters.put("ID", ID.toString());

        Object result = this.CallMethod("SendLastKnownLocation", parameters, true).toString();
        return Integer.parseInt(result.toString());

    }

    public ArrayList<User> GetUsers() throws Exception {
        ArrayList<User> users = new ArrayList<User>();
        Object result = this.CallMethod("GetUsers", null, true);
        SoapObject soapResult = (SoapObject) result;

        for (int i = 0; i < soapResult.getPropertyCount(); i++) {
            SoapObject soapUser = (SoapObject) soapResult.getProperty(i);
            User user = new User();
            user.ID = Integer.parseInt(soapUser.getProperty("ID").toString());
            user.Username = soapUser.getProperty("Username").toString();
            user.latitude = Double.parseDouble(soapUser.getProperty("latitude").toString());
            user.longitude = Double.parseDouble(soapUser.getProperty("longitude").toString());
            user.Password = soapUser.getProperty("Password").toString();
            user.email = soapUser.getProperty("email").toString();
            users.add(user);
        }

        return users;

    }

    public Object CallMethod(String methodName,
                             ConcurrentHashMap<String, String> parameters, boolean useTask) throws Exception {
        try {
            WebMethodParameters taskParameters = new WebMethodParameters();
            taskParameters.setMethodName(methodName);
            taskParameters.setNamespace(this.Namespace);
            taskParameters.setParameters(parameters);
            taskParameters.setUrl(this.Url);
            Object result = null;
            if (useTask) {
                WebServiceCallerTask caller = new WebServiceCallerTask();
                result = caller.execute(taskParameters).get();
            } else {
                WebServiceCaller caller = new WebServiceCaller();
                result = caller.CallMethod(taskParameters);
            }
            if (result instanceof Exception) {
                throw (Exception) result;
            }
            return result;
        } catch (Exception e) {
            throw e;
        }
    }
}

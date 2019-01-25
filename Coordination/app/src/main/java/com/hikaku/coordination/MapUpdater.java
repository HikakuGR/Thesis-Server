package com.hikaku.coordination;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Hikaku on 2/6/2015.
 */
public class MapUpdater  {

    private MapUpdaterParameters _parameters;
    private Thread thread;
    public MapUpdater (MapUpdaterParameters parameters){
       _parameters=parameters;
    }
    public Marker createMarker(Job job,Context context )
    {
        Integer colour;
        if(job.assigned==false)
            colour=IconGenerator.STYLE_ORANGE;
            else
            colour=IconGenerator.STYLE_RED;
        IconGenerator iconGenerator = new IconGenerator(context);
        iconGenerator.setStyle(colour);
        iconGenerator.setRotation(90);
        iconGenerator.setContentRotation(-90);
        Bitmap iconBitmap = iconGenerator.makeIcon(job.jobName);
        Marker newMarker = _parameters.get_map().addMarker(
                new MarkerOptions().position(
                        new LatLng(job.latitude, job.longitude))
                        .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))
                        .title(job.jobName));
        return newMarker;

    }

    public void Execute() {
        thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        WebServiceHandler webService = new WebServiceHandler(_parameters.get_URL());
                        ArrayList<Job> jobs = webService.GetJobs();
                        ArrayList<User> users = webService.GetUsers();
                        final ConcurrentHashMap<Integer, Marker> hashJobs = _parameters.getJobMarkers();
                        final ConcurrentHashMap<Integer, Marker> hashUsers = _parameters.getUserMarkers();

                        //Jobs
                        for (final Job job : jobs) {
                            //Enumeration<Integer> jobIDs = hashJobs.keys();
                            final Marker existingMarker = hashJobs.get(job.ID);
                            if (existingMarker != null) {
                                //refresh job position
                                _parameters.get_activity().runOnUiThread(new Runnable() {
                                    public void run() {
                                        LatLng existingLatLng = existingMarker.getPosition();
                                        double existingLatitude = existingLatLng.latitude;
                                        double existingLongitude = existingLatLng.longitude;
                                        if (existingLatitude != job.latitude && existingLongitude != job.longitude) {
                                            LatLng newLatLng = new LatLng(job.latitude, job.longitude);
                                            existingMarker.setPosition(newLatLng);

                                        }
                                        Log.d("UI thread", "jobs updated");
                                    }
                                });


                            } else {
                                //adds new Job marker
                                _parameters.get_activity().runOnUiThread(new Runnable() {
                                    public void run() {
                                        //LatLng newLatLng = new LatLng(job.latitude, job.longitude);

                                        Marker newMarker = createMarker(job,_parameters.get_activity().getApplicationContext());
                                        hashJobs.put(job.ID, newMarker);
                                        }

                                });
                            }
                        }
                        //Remove Job Marker
                        for (final Map.Entry<Integer, Marker> entry : hashJobs.entrySet()) {
                            final int jobID = entry.getKey();
                            boolean found = false;
                            for (Job job : jobs) {
                                if (job.ID == jobID) {
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                _parameters.get_activity().runOnUiThread(new Runnable() {
                                    public void run() {
                                        entry.getValue().remove();
                                        hashJobs.remove(jobID);
                                    }
                                });

                            }

                        }

                        //Users
                        for (final User user : users) {
                           // Enumeration<Integer> userIDs = hashUsers.keys();
                            final Marker existingMarker = hashUsers.get(user.ID);
                            if (existingMarker != null) {
                                //refresh user position
                                _parameters.get_activity().runOnUiThread(new Runnable() {
                                    public void run() {
                                        LatLng existingLatLng = existingMarker.getPosition();
                                        double existingLatitude = existingLatLng.latitude;
                                        double existingLongitude = existingLatLng.longitude;
                                        if (existingLatitude != user.latitude && existingLongitude != user.longitude) {
                                            LatLng newLatLng = new LatLng(user.latitude, user.longitude);
                                            existingMarker.setPosition(newLatLng);

                                        }
                                        Log.d("UI thread", "users updated");
                                    }
                                });


                            } else {
                                //adds new user marker
                                _parameters.get_activity().runOnUiThread(new Runnable() {
                                    public void run() {
                                        //LatLng newLatLng = new LatLng(user.latitude, user.longitude);
                                        IconGenerator iconGenerator = new IconGenerator(_parameters.get_activity().getApplicationContext());
                                        iconGenerator.setStyle(iconGenerator.STYLE_BLUE);
                                        iconGenerator.setRotation(90);
                                        iconGenerator.setContentRotation(-90);
                                        Bitmap iconBitmap = iconGenerator.makeIcon(user.Username);
                                        Marker newMarker = _parameters.get_map().addMarker(
                                                new MarkerOptions().position(
                                                        new LatLng(user.latitude, user.longitude))
                                                        .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))
                                                        .title(user.Username));
                                        //.icon(BitmapDescriptorFactory.fromAsset("userMarker.png"))
                                        hashUsers.put(user.ID, newMarker);
                                    }
                                });
                            }
                        }
                        //Remove user Marker
                        for (final Map.Entry<Integer, Marker> entry : hashUsers.entrySet()) {
                            final int userID = entry.getKey();
                            boolean found = false;
                            for (User user : users) {
                                if (user.ID == userID) {
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                _parameters.get_activity().runOnUiThread(new Runnable() {
                                    public void run() {
                                        entry.getValue().remove();
                                        hashUsers.remove(userID);
                                    }
                                });

                            }

                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            Thread.sleep(5000);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            }

        });

        thread.start();
    }

}


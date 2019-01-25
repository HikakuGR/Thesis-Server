package com.hikaku.coordination;

import android.app.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Hikaku on 2/6/2015.
 */
public class MapUpdaterParameters {
    private ConcurrentHashMap<Integer, Marker> _jobMarkers;



    private ConcurrentHashMap<Integer, Marker> _userMarkers;
    private GoogleMap _map;
    private Activity _activity;
    private String _URL;

    public Activity get_activity() {
        return _activity;
    }

    public MapUpdaterParameters(Activity activity ,ConcurrentHashMap<Integer, Marker> jobMarkers, ConcurrentHashMap<Integer, Marker> userMarkers, GoogleMap map, String URL) {
        _jobMarkers = jobMarkers;
        _userMarkers = userMarkers;
        _map = map;
        _URL = URL;
        _activity=activity;
    }

    public ConcurrentHashMap<Integer, Marker> getUserMarkers() {
        return _userMarkers;
    }
    public ConcurrentHashMap<Integer, Marker> getJobMarkers() {
        return _jobMarkers;
    }

    public GoogleMap get_map() {
        return _map;
    }

    public String get_URL() {
        return _URL;
    }

}

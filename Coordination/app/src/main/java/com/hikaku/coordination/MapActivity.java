package com.hikaku.coordination;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.BubbleIconFactory;
import com.google.maps.android.ui.IconGenerator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class MapActivity extends FragmentActivity {

	// Google Map
	private GoogleMap googleMap;
	private MapUpdater mapUpdater;
	ConcurrentHashMap< Integer,Marker> hashUsers , hashJobs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {


		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		hashUsers = new ConcurrentHashMap< Integer,Marker>();
		hashJobs = new ConcurrentHashMap< Integer,Marker>();
		try {
			// Loading map
			initializeMap();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		try {
			initializeMap();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	//public void onMapReady(GoogleMap map) throws Exception {
		//String serverAddress = Constants.getServerAddress(getApplicationContext().getSharedPreferences(Constants.PREFERENCES_NAME,MODE_PRIVATE));
		//WebServiceHandler webservice = new WebServiceHandler(serverAddress);
		//ArrayList<Job> jobs = webservice.GetJobs();
		//ArrayList<User> users = webservice.GetUsers();
		/*for (Job job : jobs) {
			if(job.completed == false){
			IconGenerator iconGenerator = new IconGenerator(this);
			iconGenerator.setStyle(IconGenerator.STYLE_RED);
			iconGenerator.setRotation(90);
			iconGenerator.setContentRotation(-90);
			Bitmap iconBitmap = iconGenerator.makeIcon(job.jobName);

			Marker marker = map.addMarker(
					new MarkerOptions().position(
							new LatLng(job.latitude, job.longitude))
							.icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))
							.title(job.jobName));
			hashJobs.put(job.ID, marker);};
		}
		

		for (User user : users) {
			IconGenerator iconGenerator = new IconGenerator(this);
			iconGenerator.setStyle(IconGenerator.STYLE_BLUE);
			iconGenerator.setRotation(90);
			iconGenerator.setContentRotation(-90);
			Bitmap iconBitmap = iconGenerator.makeIcon(user.Username);
			Marker marker = map.addMarker(
					new MarkerOptions().position(
							new LatLng(user.latitude, user.longitude))
							.icon(BitmapDescriptorFactory.fromBitmap(iconBitmap)).title(
							user.Username));
			hashUsers.put(user.getID(),marker);
		}
	}*/



	private void initializeMap() throws Exception {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			//onMapReady(googleMap);
			googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
				@Override
				public boolean onMarkerClick(Marker marker) {

					if (hashJobs.containsValue(marker)) {
						Integer foundJobID = -1;
						for (Map.Entry<Integer, Marker> entrySet : hashJobs.entrySet()) {
							if (entrySet.getValue().getId().equals(marker.getId())) {
								foundJobID = entrySet.getKey();
								break;
							}
						}
						if (foundJobID != -1) {
							try {
								String serverAddress = Constants.getServerAddress(getApplicationContext().getSharedPreferences(Constants.PREFERENCES_NAME, MODE_PRIVATE));
								WebServiceHandler webservice = new WebServiceHandler(serverAddress);
								Job job = webservice.GetJobByID(foundJobID);
								Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
								intent.putExtra("job", job);
								startActivity(intent);
							} catch (Exception EX){
								EX.printStackTrace();
							}
						}
					}

						return false;
					}
				}

				);

				// check if map is created successfully or not
				if(googleMap==null)

				{
					Toast.makeText(getApplicationContext(),
							"Sorry! unable to create maps", Toast.LENGTH_SHORT)
							.show();
				}
			}
			if (mapUpdater==null){
				String serverAddress = Constants.getServerAddress(getApplicationContext().getSharedPreferences(Constants.PREFERENCES_NAME, MODE_PRIVATE));
				MapUpdaterParameters mapUpdaterParameters = new MapUpdaterParameters(this ,hashJobs,hashUsers,googleMap,serverAddress);
				mapUpdater = new MapUpdater(mapUpdaterParameters);
				mapUpdater.Execute();
			}

		}



	}

package com.example.locationtest;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView positionTextView;
	private LocationManager locationManager;
	private String provider;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		positionTextView = (TextView) findViewById(R.id.position_text_view);
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		List<String> providerList = locationManager.getProviders(true);
		if(providerList.contains(LocationManager.GPS_PROVIDER)){
			provider = LocationManager.GPS_PROVIDER;
		}else if(providerList.contains(locationManager.NETWORK_PROVIDER)){
			provider = locationManager.NETWORK_PROVIDER;
		}else{
			Toast.makeText(this, "No location provider to use", Toast.LENGTH_SHORT).show();
			return;
		}
		Location location = locationManager.getLastKnownLocation(provider);
		if (location != null){
			showLocation(location);
		}
		locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
	}
	
	protected void onDestroy(){
		super.onDestroy();
		if(locationManager != null){
			locationManager.removeUpdates(locationListener);
		}
	}
	
	LocationListener locationListener = new LocationListener(){
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras){
			
		}
		
		@Override
		public void onProviderEnabled(String provider){
			
		}
		
		@Override
		public void onProviderDisabled(String provider){
			
		}
		
		@Override
		public void onLocationChanged(Location location){
			showLocation(location);
		}
	};

	
	private void showLocation(Location location){
		String currentPositon = "latitude is" + location.getLatitude() + "\n" + "longitude is " + location.getLongitude();
		positionTextView.setText(currentPositon);
	}

}

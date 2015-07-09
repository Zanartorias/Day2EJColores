package com.android.dango.day2ejcolores;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;


public class Act_GPS extends AppCompatActivity {

    List<Address> addressList;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__gps);
        addressList = null;
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Geocoder gc = new Geocoder(getApplicationContext());
                try{
                    addressList = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 5);
                }catch(IOException e){
                    e.printStackTrace();
                }

                for (int i = 0; i < addressList.size(); i++) {
                    Log.v("Log", addressList.get(i).getAddressLine(0).toString());
                    TextView t = (TextView) findViewById(R.id.TV);
                    if (i == 0) t.setText("");
                        t.setText((t.getText()+"\n"+addressList.get(i).getAddressLine(0).toString()));
                }
                Log.v("Log",((Double) location.getLatitude()).toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    @Override
    protected void onPause(){
        locationManager.removeUpdates(locationListener);//.removeGpsStatusListener((GpsStatus.Listener)locationListener);
        locationManager = null;
        super.onPause();
    }


}

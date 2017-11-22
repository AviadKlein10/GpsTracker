package com.example.aviad.gpstracker;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnLocationChangedCallBack {

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (PermissionsUtils.askPermissionLocation(this)) {
            GPSTracker gpsTracker = new GPSTracker(this,this);
            if (!gpsTracker.canGetLocation()) {
                gpsTracker.showSettingsAlert();
            }
        }

    }

    @Override
    public void onLocationChanged(double latitude, double longitude) {
        counter++;
        Log.d("onchanged", latitude+"");
        ((TextView)findViewById(R.id.hello)).setText("up" + counter+"");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 2222: {//Constants.MY_PERMISSIONS_REQUEST_
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        Log.d("granted","hey");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

        }
    }
}

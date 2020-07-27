package com.refknowledgebase.refknowledgebase.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.refknowledgebase.refknowledgebase.Activity_Sec;
import com.refknowledgebase.refknowledgebase.BuildConfig;
import com.refknowledgebase.refknowledgebase.DashboardActivity;
import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.GeocodingLocation;

import java.util.ArrayList;
import static android.content.Context.MODE_PRIVATE;
import static com.facebook.login.widget.ProfilePictureView.TAG;
import static com.parse.Parse.getApplicationContext;
import static com.refknowledgebase.refknowledgebase.utils.Constant.countries;
import static com.refknowledgebase.refknowledgebase.utils.Constant.language;

public class SettingFragment extends Fragment implements View.OnClickListener{

    RelativeLayout rl_sec_country, rl_sec_language;
    TextView tv_sec_lanugage, tv_sec_country, tv_save;
    ToggleButton switch_location, switch_dark_mode, switch_notification;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;
    ArrayList<LatLng> points;
    private SettingsClient mSettingsClient;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationRequest mLocationRequest;
    private static final int REQUEST_CHECK_SETTINGS = 100;
    private Boolean mRequestingLocationUpdates;
    LocationManager locationManager;

    private static final int REQUEST_LOCATION = 1;

    String latitude, longitude;

    public SettingFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setting, container, false);

        tv_sec_country = root.findViewById(R.id.tv_sec_country);
        tv_sec_lanugage = root.findViewById(R.id.tv_sec_lanugage);
        rl_sec_country = root.findViewById(R.id.rl_sec_country);
        rl_sec_language = root.findViewById(R.id.rl_sec_language);
        switch_location = root.findViewById(R.id.switch_location);
        switch_dark_mode = root.findViewById(R.id.switch_dark_mode);
        switch_notification = root.findViewById(R.id.switch_notification);
        tv_save = root.findViewById(R.id.tv_save);
        tv_save.setOnClickListener(this);

        mRequestingLocationUpdates = false;
//init
        if (!getString(Constant.SELECTED_COUNTRY).equals(""))
        tv_sec_country.setText(getString(Constant.SELECTED_COUNTRY));
        if (!getString(Constant.SELECTED_LANGUAGE).equals(""))
        tv_sec_lanugage.setText(getString(Constant.SELECTED_LANGUAGE));

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mSettingsClient = LocationServices.getSettingsClient(getActivity());

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // location is received
                mCurrentLocation = locationResult.getLastLocation();

                updateLocationUI();
            }
        };

        restoreValuesFromBundle(savedInstanceState);

        init();
        return root;
    }

    private void init() {
        if (getBoolean(Constant.SET_LOCATION)){
            switch_location.setChecked(true);
        }else {
            switch_location.setChecked(false);
        }
        if (getBoolean(Constant.SET_DARK)){
            switch_dark_mode.setChecked(true);
        }else {
            switch_dark_mode.setChecked(false);
        }
        if (getBoolean(Constant.SET_PUSH)){
            switch_notification.setChecked(true);
        }else {
            switch_notification.setChecked(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mRequestingLocationUpdates && checkPermissions()) {
            startLocationUpdates();
        }
        updateLocationUI();
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void restoreValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("is_requesting_updates")) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
            }

            if (savedInstanceState.containsKey("last_known_location")) {
                mCurrentLocation = savedInstanceState.getParcelable("last_known_location");
            }

            if (savedInstanceState.containsKey("last_updated_on")) {
            }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (mCurrentLocation != null) {
            Toast.makeText(getApplicationContext(), "Lat: " + mCurrentLocation.getLatitude()
                    + ", Lng: " + mCurrentLocation.getLongitude(), Toast.LENGTH_LONG).show();
            LatLng latlng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
            points.add(latlng);
        }
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rl_sec_country.setOnClickListener(this);
        rl_sec_language.setOnClickListener(this);

        switch_location.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
            if(isChecked){
                Dexter.withActivity(getActivity())
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
                            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                                OnGPS();
                            }else {
                                getLocation();
                            }
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            if (response.isPermanentlyDenied()) {
                                openSettings();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();
            }
            else{
                stopLocationUpdates();
            }
            }
        });
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                getContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
            } else {
                Toast.makeText(getContext(), "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startLocationUpdates() {
        mSettingsClient
            .checkLocationSettings(mLocationSettingsRequest)
            .addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
                @SuppressLint("MissingPermission")
                @Override
                public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                    Log.i(TAG, "All location settings are satisfied.");

                    Toast.makeText(getApplicationContext(), "Started location updates!", Toast.LENGTH_SHORT).show();
                    mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                            mLocationCallback, Looper.myLooper());
                    updateLocationUI();
                }
            })
            .addOnFailureListener(getActivity(), new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    int statusCode = ((ApiException) e).getStatusCode();
                    switch (statusCode) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                    "location settings ");
                            try {
                                // Show the dialog by calling startResolutionForResult(), and check the
                                // result in onActivityResult().
                                ResolvableApiException rae = (ResolvableApiException) e;
                                rae.startResolutionForResult(getActivity(), REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException sie) {
                                Log.i(TAG, "PendingIntent unable to execute request.");
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            String errorMessage = "Location settings are inadequate, and cannot be " +
                                    "fixed here. Fix in Settings.";

                            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                            break;
                    }
                    updateLocationUI();
                }
            });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_sec_country:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select Country");
                builder.setItems(countries, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    tv_sec_country.setText(countries[which]);

                    insertString("MAPCOUNTRY",countries[which]);
                    GeocodingLocation locationAddress = new GeocodingLocation();
                    locationAddress.getAddressFromLocation(countries[which],
                            getContext(), new GeocoderHandler());
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.rl_sec_language:
                AlertDialog.Builder builder_lan = new AlertDialog.Builder(getContext());
                builder_lan.setTitle("Select Language");
                builder_lan.setItems(language, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_sec_lanugage.setText(language[which]);
                    }
                });
                AlertDialog dialog_lan = builder_lan.create();
                dialog_lan.show();
                break;

            case R.id.tv_save:
                if (switch_location.isChecked()){
                    insertBoolean(Constant.SET_LOCATION, true);
                }else {
                    insertBoolean(Constant.SET_LOCATION, false);
                }
                if (switch_dark_mode.isChecked()){
                    insertBoolean(Constant.SET_DARK, true);
                }else {
                    insertBoolean(Constant.SET_DARK, false);
                }
                if (switch_notification.isChecked()){
                    insertBoolean(Constant.SET_PUSH, true);
                }else {
                    insertBoolean(Constant.SET_PUSH, false);
                }
                insertString(Constant.SELECTED_COUNTRY, tv_sec_country.getText().toString());
                insertString(Constant.SELECTED_LANGUAGE, tv_sec_lanugage.getText().toString());
                startActivity(new Intent(getContext(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                break;
        }
    }

    private void stopLocationUpdates() {
        mFusedLocationClient
            .removeLocationUpdates(mLocationCallback)
            .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "Location Track stopped", Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void openSettings() {
        Intent intent = new Intent();
        intent.setAction( Settings.ACTION_APPLICATION_DETAILS_SETTINGS );
        Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public synchronized void insertBoolean(String key, boolean value) {
        SharedPreferences mSharedPreferences = getContext().getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }
    public synchronized boolean getBoolean(String key) {
        SharedPreferences mSharedPreferences = getContext().getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        Boolean  selected =  mSharedPreferences.getBoolean(key, false);
        return selected;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        break;
                    case Activity.RESULT_CANCELED:
                        mRequestingLocationUpdates = false;
                        break;
                }
                break;
        }
    }
    public String getString(String key) {
        SharedPreferences mSharedPreferences = getContext().getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        String  selected =  mSharedPreferences.getString(key, "");
        return selected;
    }

    public synchronized void insertString(String key, String value) {
        SharedPreferences mSharedPreferences = getContext().getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.apply();
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            String[] location = locationAddress.split(",");
            mBuffer.map_lat = location[0];
            mBuffer.map_long = location[1];
            insertString("MAPLAT", location[0]);
            insertString("MAPLONG", location[1]);
        }
    }
}
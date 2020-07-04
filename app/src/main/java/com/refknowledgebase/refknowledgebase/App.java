package com.refknowledgebase.refknowledgebase;

import android.app.Application;

import com.facebook.appevents.AppEventsLogger;
import com.parse.Parse;
import com.parse.facebook.ParseFacebookUtils;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if desired
                .clientKey(getString(R.string.back4app_client_key))
                .server("https://parseapi.back4app.com/")
                .build()
        );
        ParseFacebookUtils.initialize(this);
    }
}

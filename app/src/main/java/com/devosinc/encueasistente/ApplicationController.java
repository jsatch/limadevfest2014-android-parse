package com.devosinc.encueasistente;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;

/**
 * Created by hquintana on 7/11/14.
 */
public class ApplicationController extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        // Cambiar XXX e YYY por los datos de su aplicacion
        Parse.initialize(this, "XXX", "YYY");
        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }

        });
    }
}

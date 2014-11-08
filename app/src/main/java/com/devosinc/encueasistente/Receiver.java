package com.devosinc.encueasistente;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

/**
 * Created by hquintana on 8/11/14.
 */
public class Receiver extends ParsePushBroadcastReceiver {

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        Intent i = new Intent(context, VerPreguntaActivity.class);

        Log.i("Parse", "Entra onPushOpen");

        i.putExtras(intent.getExtras());

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}

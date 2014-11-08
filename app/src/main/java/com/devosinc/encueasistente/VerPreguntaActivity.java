package com.devosinc.encueasistente;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePushBroadcastReceiver;
import com.parse.SaveCallback;

import org.json.JSONException;
import org.json.JSONObject;


public class VerPreguntaActivity extends Activity implements View.OnClickListener{
    TextView tviPregunta;
    Button butResponder;
    RadioButton rbuSi, rbuNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pregunta);

        tviPregunta = (TextView) findViewById(R.id.tviPregunta);
        butResponder = (Button) findViewById(R.id.butResponder);
        rbuSi = (RadioButton) findViewById(R.id.rbuSi);
        rbuNo = (RadioButton) findViewById(R.id.rbuNo);

        butResponder.setOnClickListener(this);

        try {
            if (getIntent().getStringExtra(ParsePushBroadcastReceiver.KEY_PUSH_DATA) != null) {
                JSONObject js = new JSONObject(getIntent().getStringExtra(ParsePushBroadcastReceiver.KEY_PUSH_DATA));
                tviPregunta.setText(js.getString("alert"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ver_pregunta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        ParseObject respuesta = new ParseObject("Respuesta");

        respuesta.put("Si", rbuSi.isChecked());
        respuesta.put("pregunta", tviPregunta.getText().toString());

        respuesta.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Toast.makeText(VerPreguntaActivity.this,
                        "Guardado correctamente", Toast.LENGTH_LONG).show();
                tviPregunta.setText("");
            }
        });

    }
}

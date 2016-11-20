package com.example.harshdeepharshdeep.barcode_reader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.harshdeepharshdeep.barcode_reader.Service.FoodObject;
import com.example.harshdeepharshdeep.barcode_reader.Service.Tools;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.plattysoft.leonids.ParticleSystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONObject;

import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NukeSSLCerts.nuke();
        setContentView(R.layout.activity_main);
        scanBtn = (Button) findViewById(R.id.scan_button);
        formatTxt = (TextView) findViewById(R.id.scan_format);
        contentTxt = (TextView) findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);
        // setContentView(R.layout.activity_confetti_example);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.scan_button) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        String scanContent = "", scanFormat = "";
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null)
        {
            scanContent = scanningResult.getContents();
            scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);

        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }


        final TextView mTextView = (TextView) findViewById(R.id.scan_format);
        RequestQueue queue = Volley.newRequestQueue(this);
        //String url = "https://www.openfood.ch/api/v1/products?barcodes[]=3046920022651";
        String url = Tools.createGetBarcodeUrl(scanContent);
        Log.v("main", url);

        FoodObject fObg;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("main", "RESPONSE RECEIVED");
                        // Display the first 500 characters of the response string.
                        String jsonString = response;
                        FoodObject fObj =  Tools.getFoodObjectFromJsonString(jsonString);
                        mTextView.setText(fObj.getName() + "\n" + fObj.toString());
                        if(!fObj.isError){
                            double sugar = fObj.nuts.get("Sugars").getVal();
                            double carb = fObj.nuts.get("Carbohydrates").getVal();
                            double fat = fObj.nuts.get("Fat").getVal();
                            draw_magicStuff((int)sugar,(int)carb,(int)fat);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("main", "RESPONSE NOT WORK " + error.getMessage());
                mTextView.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

    public void draw_magicStuff(int s, int c, int f ){
        final double factor = 0.2;
        new ParticleSystem(this, (15 *  (int)Math.ceil(factor * s)), R.drawable.confeti2, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.1f, 180, 180)
                .setRotationSpeed(144)
                .setAcceleration(0.00007f, 90)
                .emit(findViewById(R.id.emiter_top_right), (int)Math.ceil(factor * s), 4000);

        new ParticleSystem(this, 15 *  (int)Math.ceil(factor * c), R.drawable.confeti3, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.00007f, 90)
                .emit(findViewById(R.id.emiter_top_left), (int)Math.ceil(factor* c), 4000);

        new ParticleSystem(this, 15* (int)Math.ceil(factor * f), R.drawable.confeti4, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.00007f, 90)
                .emit(findViewById(R.id.emiter_top_center), (int)Math.ceil( factor*f) , 4000);
    }

}

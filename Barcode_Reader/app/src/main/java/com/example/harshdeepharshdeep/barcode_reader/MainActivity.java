package com.example.harshdeepharshdeep.barcode_reader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
<<<<<<< HEAD
import com.example.harshdeepharshdeep.barcode_reader.Service.FoodObject;
import com.example.harshdeepharshdeep.barcode_reader.Service.Tools;
=======
>>>>>>> c253b506d14f7e43e33edd96a86d9f2c6bc87538
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

<<<<<<< HEAD
import org.json.JSONObject;

=======
>>>>>>> c253b506d14f7e43e33edd96a86d9f2c6bc87538
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
<<<<<<< HEAD
        }
        else
        {
=======
        } else {
>>>>>>> c253b506d14f7e43e33edd96a86d9f2c6bc87538
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }


        final TextView mTextView = (TextView) findViewById(R.id.scan_format);
        RequestQueue queue = Volley.newRequestQueue(this);
<<<<<<< HEAD
        //String url = "https://www.openfood.ch/api/v1/products?barcodes[]=3046920022651";
        String url = Tools.createGetBarcodeUrl(scanContent);
=======
        String url = "https://www.openfood.ch/api/v1/products?barcodes[]=3046920022651";
>>>>>>> c253b506d14f7e43e33edd96a86d9f2c6bc87538
        Log.v("main", url);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("main", "RESPONSE RECEIVED");
                        // Display the first 500 characters of the response string.
<<<<<<< HEAD
                        String jsonString = response;
                        FoodObject fObj =  Tools.getFoodObjectFromJsonString(jsonString);
                        mTextView.setText(fObj.getName() + "\n" + fObj.toString());
=======
                        mTextView.setText("Response is: " + response.substring(0, 500));
>>>>>>> c253b506d14f7e43e33edd96a86d9f2c6bc87538
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
}

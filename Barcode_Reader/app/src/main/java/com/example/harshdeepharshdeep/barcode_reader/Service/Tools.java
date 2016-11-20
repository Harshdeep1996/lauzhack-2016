package com.example.harshdeepharshdeep.barcode_reader.Service;

/**
 * Created by martinli on 2016-11-20.
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Tools {

    static String URL = "https://www.openfood.ch/api/v1/";
    static String BARCODE_REQUEST = "products?barcodes[]=" ;
    static String PRODUCT_ID_REQUEST = "product/" ;

    public static String createGetBarcodeUrl (String barcode)
    {
        return URL + BARCODE_REQUEST + barcode;
    }

    public static FoodObject getFoodObjectFromBarcode(String barcode) {
        JSONObject response = sendBarcodeReq(barcode);
        if(response.equals(null))
        {
            FoodObject ret = new FoodObject();
            ret.setName("Error: json response is null");
            return ret;
        }
        return parseFoodItem(response);
    }

    public static FoodObject getFoodObjectFromJsonString(String jsonString) {
        if(jsonString.contains("\"data\":[]"))
        {
            FoodObject ret = new FoodObject();
            ret.setName("Error: json response is null");
            return ret;
        }
        JSONObject response = processJsonString(jsonString);

        return parseFoodItem(response);
    }

    private static void addIngredients (JSONObject foodJson,FoodObject fObj) {

        JSONObject ingred = null;
        try {
            ingred = foodJson.getJSONObject("ingredients-translations");
            Iterator it = ingred.keys();
            String str = "";
            while (it.hasNext()) {
                String key = (String)it.next();
                String val = ingred.getString(key);
                str += (val + "\n");
            }
            str = (str.replace(',', '\n').replace(')','\n').replace('(','\n'));
            fObj.addIngredients(str);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static FoodObject parseFoodItem (JSONObject foodJson) {
        FoodObject ret = new FoodObject();
        try {
            JSONArray nutrients = foodJson.getJSONArray("nutrients");
            String name = foodJson.getString("name");
            ret.setName(name);
            int length = nutrients.length();
            for( int i = 0 ; i < length ;i ++ ){
                Nutrient thisNut = new Nutrient();
                thisNut.update(nutrients.getJSONObject(i));
                ret.addNut(thisNut);
            }

            addIngredients(foodJson,ret);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ret;


    }

    public static JSONObject sendBarcodeReq(String barcode) {
        try {
            String testBarcodeRespone = getHTMLAsJson(URL + BARCODE_REQUEST + barcode);
            return processJsonString(testBarcodeRespone);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    public static JSONObject processJsonString (String json) {
        try {
            JSONObject response = new JSONObject(json);
            return  ((JSONObject)response.getJSONArray("data").get(0)).getJSONObject("attributes");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static String getHTMLAsJson(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }


}


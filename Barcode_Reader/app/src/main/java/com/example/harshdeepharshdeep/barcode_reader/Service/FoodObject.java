package com.example.harshdeepharshdeep.barcode_reader.Service;

/**
 * Created by martinli on 2016-11-20.
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FoodObject {
    private HashMap<String, Nutrient> nuts;
    private String name ;
    public FoodObject() {
        nuts = new HashMap<>();
        setName("");
    }

    public void addNut(Nutrient nu) {
        nuts.put(nu.getName(), nu);
    }

    public String toString() {
        Iterator it = nuts.entrySet().iterator();
        String ret = "";
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            ret += (pair.getKey() + " = " + pair.getValue() + "\n");
        }
        return ret;
    }
    public Nutrient getNut(String name)
    {
        return nuts.get(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

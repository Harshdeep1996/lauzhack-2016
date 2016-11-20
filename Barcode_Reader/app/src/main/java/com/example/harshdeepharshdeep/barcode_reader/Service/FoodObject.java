package com.example.harshdeepharshdeep.barcode_reader.Service;

/**
 * Created by martinli on 2016-11-20.
 */

import java.util.HashMap;

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

    public Nutrient getNut(String name)
    {
        return nuts.get(name);
    }
    public String toString(){
        return "name: " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

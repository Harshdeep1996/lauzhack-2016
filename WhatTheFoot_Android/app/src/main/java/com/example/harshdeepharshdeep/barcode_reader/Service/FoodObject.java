package com.example.harshdeepharshdeep.barcode_reader.Service;

/**
 * Created by martinli on 2016-11-20.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FoodObject {
    public HashMap<String, Nutrient> nuts;
    public boolean isError = false;
    private ArrayList<String> ingredients;
    private String name ;
    public FoodObject() {
        ingredients = new ArrayList<>();
        nuts = new HashMap<>();
        setName("");
    }
    public Nutrient get (String key)
    {
        Nutrient ret = null ;
        ret = nuts.get(key);

        return ret == null ? new Nutrient () : ret;
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
        ret += "\n";
        for (String s : ingredients)
        {
            ret += s + "\n"; 
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

    public void addIngredients(String ingred) {
        String[] ing = ingred.split("\n");
        for(String s : ing)
        {
            if(!s.equals("")){
                s = s.trim();
                ingredients.add(s);
            }
        }
    }
}

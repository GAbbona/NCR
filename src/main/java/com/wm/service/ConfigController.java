/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wm.service;

import com.wm.entity.Denominations;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;

/**
 *
 * @author WM
 */
public class ConfigController {

    public List<Denominations> readFromFile() throws FileNotFoundException {

        InputStream configFile = new FileInputStream(new File("resources/config.json"));

        JsonObject jsonObj;
        try (JsonReader reader = Json.createReader(configFile)) {
            jsonObj = reader.readObject();
        }
        JsonArray noms = jsonObj.getJsonArray("denominations");
        return noms.stream().map(JsonValue::asJsonObject)
                .map(o -> new Denominations(o.getInt("id"), o.getInt("value"),
                o.getString("level"), o.getInt("cuantity")))
                .collect(Collectors.toList());

    }

    public void writeToFile(List<Denominations> nominations) throws FileNotFoundException {

        InputStream inFile = new FileInputStream(new File("resources/config.json"));

        JsonObject jsonObj;
        try (JsonReader reader = Json.createReader(inFile);) {

            jsonObj = reader.readObject();

            JsonBuilderFactory jsonBuilderFactory = Json.createBuilderFactory(null);

            JsonArrayBuilder jsonArrayBuilder = jsonBuilderFactory.createArrayBuilder();
            JsonObjectBuilder jsonMainObj = jsonBuilderFactory.createObjectBuilder();

            JsonArray noms = jsonObj.getJsonArray("denominations");

            noms.stream().map((nom) -> {
                Denominations persist = null;
                JsonObject o = nom.asJsonObject();
                for (Denominations nomination : nominations) {
                    if (o.getInt("id") == nomination.getId()) {
                        persist = nomination;
                    }
                }
                JsonObjectBuilder l = jsonBuilderFactory.createObjectBuilder();
                if (persist != null) {
                    l.add("id", persist.getId());
                    l.add("value", persist.getValue());
                    l.add("level", persist.getLevel());
                    l.add("cuantity", persist.getCuantity());
                } else {
                    l.add("id", o.getInt("id"));
                    l.add("value", o.getInt("value"));
                    l.add("level", o.getString("level"));
                    l.add("cuantity", o.getInt("cuantity"));
                }
                return l;
            }).forEachOrdered((l) -> {
                jsonArrayBuilder.add(l.build());
            });

            jsonMainObj.add("denominations", jsonArrayBuilder);
            OutputStream outFile = new FileOutputStream(new File("resources/config.json"));

            JsonWriter jsonWriter = Json.createWriter(outFile);
            jsonWriter.writeObject(jsonMainObj.build());
        }

    }

}

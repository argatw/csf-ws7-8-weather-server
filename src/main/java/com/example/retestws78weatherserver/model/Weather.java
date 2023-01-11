package com.example.retestws78weatherserver.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class Weather {

    public String city;
    private String description;
    // public String id;
    // public String getId() {
    //     return id;
    // }
    // public void setId(String id) {
    //     this.id = id;
    // }
    // private List<String> conditions = new LinkedList<>();
    // public List<String> getConditions() {
    //     return conditions;
    // }
    // public void setConditions(List<String> conditions) {
    //     this.conditions = conditions;
    // }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    // String id = UUID.randomUUID().toString().substring(0,4);

    public JsonObject cityToJson() {
        // List<Conditions> t = new LinkedList<>();
        // // JsonObject o = Json.createReader(new StringReader(payload)).readObject();

        // for (int i = 0; i < conditions.size(); i++) {
        //     t.add(conditions);
        // }
        return Json.createObjectBuilder()
            // .add("id", id)
            .add("city", city) 
            // .add("conditions", description) 
            .build();
    }
    

    public JsonObject weatherToJson() {
        // List<Conditions> t = new LinkedList<>();
        // // JsonObject o = Json.createReader(new StringReader(payload)).readObject();

        // for (int i = 0; i < conditions.size(); i++) {
        //     t.add(conditions);
        // }
        return Json.createObjectBuilder()
            // .add("id", id)
            .add("city", city) 
            .add("conditions", description) 
            .build();
    }

    public static Weather createRs(SqlRowSet rs) {
        Weather w = new Weather();
        w.setCity(rs.getString("city"));
        // w.setId(rs.getString("id"));
        return w;
    }

    public static Weather create(String json) {
        JsonReader r = Json.createReader(new StringReader(json));
        JsonObject o = r.readObject();

        Weather weather = new Weather();
        // weather.setId(o.getString("id"));
        weather.setCity(o.getString("city"));
        return weather;

    }

    // public static Weather create(String json) throws IOException{
    //     Weather w = new Weather();
    //     try(InputStream is = new ByteArrayInputStream(json.getBytes())) {
    //         JsonReader r = Json.createReader(is);
    //         JsonObject o = r.readObject();
    //         w.city = o.getString("name");
    //         w.conditions = o.getJsonArray("weather")
    //             .stream()
    //             .map(v -> (JsonObject)v)  // returns a stream consisting of result of applying given function to the elements of this stream
    //             .map(v -> Conditions.create(v))
    //             .toList();
            
    //     }

    //     return w;
    // }
    
}

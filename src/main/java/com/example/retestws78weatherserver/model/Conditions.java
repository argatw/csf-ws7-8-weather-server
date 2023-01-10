package com.example.retestws78weatherserver.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Conditions {
    private String description;
    public String city;

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    // public String getIcon() {
    //     return icon;
    // }
    // public void setIcon(String icon) {
    //     this.icon = icon;
    // }

    // public static Conditions create(JsonObject o) {
    //     Conditions c = new Conditions();
    //     c.description = 
    // }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("description", description) 
            .add("city", city) 
            .build();
    }

    // public static Conditions create(JsonObject o){
    //     Conditions c = new Conditions();
    //     c.description = "%s - %s".formatted(o.getString("main"), o.getString("description"));
    //     return c;
    // }
}

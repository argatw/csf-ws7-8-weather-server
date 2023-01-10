package com.example.retestws78weatherserver.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.example.retestws78weatherserver.model.Conditions;
import com.example.retestws78weatherserver.model.Response;
import com.example.retestws78weatherserver.model.Weather;
import com.example.retestws78weatherserver.service.WeatherService;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/weather" , produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherController {

    @Autowired
    private WeatherService wService;

    // for city-detail component
    @GetMapping(path="{city}")
    public ResponseEntity<String> getCityWeather(@PathVariable String city) {
        Optional<Weather> o = wService.getWeather(city);

        List<Conditions> conditions = new LinkedList<>();
        JsonArrayBuilder ab = Json.createArrayBuilder();

        if(o.isEmpty()) {
            Response r =  new Response();
            r.setStatus(404);
            r.setMessage("City %s not found".formatted(city));
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(r.toJson().toString());
        }
        // for (Conditions c2 : conditions) {
        //     ab.add(c2.toJson());
        // }

        Weather w = o.get();
        // Conditions c = o.get();

        return ResponseEntity.ok(w.toJson().toString());
    }

    // // for cities-list comp
    // @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<String> getFavItems() {
    //     List<Weather> favCities = wService.getListOfCities();
    //     JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    //     for (Weather weather: favCities)
    //         arrayBuilder.add(weather.toJson());    
    //     return ResponseEntity.ok(arrayBuilder.build().toString());
    // }

    // //for cities-list comp
    // @PostMapping(path = "/addcity", consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<String> insertFavItems(@RequestBody String payload) {

    //     Weather cityItem = Weather.create(payload);

    //     wService.insertCities(cityItem);

    //     JsonObject data = Json.createObjectBuilder()
    //                 .add("id", cityItem.getId())
    //                 .add("city", cityItem.getCity())
    //                 .build();


    //     return ResponseEntity.status(HttpStatus.CREATED).body(data.toString());
    // }

    // // for cities-list comp
    // @DeleteMapping(path = "/delete")
    // public ResponseEntity<String> deleteCityItem(@RequestParam String city) {
    //     try {
    //         wService.deleteCity(city);
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         System.out.println(e.getMessage());
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
    //     }

    //     Response r = new Response();
    //     r.setMessage("Fav city delete success.");
    //     r.setStatus(200);

    //     return ResponseEntity.status(HttpStatus.OK).body(r.toJson().toString());

    // }
}

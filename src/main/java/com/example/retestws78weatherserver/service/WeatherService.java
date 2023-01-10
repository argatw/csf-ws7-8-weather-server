package com.example.retestws78weatherserver.service;

import java.io.StringReader;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.retestws78weatherserver.model.Conditions;
import com.example.retestws78weatherserver.model.Weather;
// import com.example.retestws78weatherserver.repository.WeatherRepo;

import jakarta.annotation.PostConstruct;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class WeatherService {
    private Logger logger = Logger.getLogger(WeatherService.class.getName());

    // @Autowired
    // private WeatherRepo wRepo;

    private static final String URL = "https://api.openweathermap.org/data/2.5/weather";
    // SET OPEN_WEATHER_MAP =<your api> //in cmd, no space aft =
    @Value("${open.weather.map}")
    private String apiKey;
    private boolean hasKey;

    @PostConstruct private void init() {
        hasKey = null != apiKey;
        logger.info(">>>API KEY SET: %s = %s".formatted(hasKey,apiKey));
    }

    public Optional<Weather> getWeather(String city) {
        Weather w = new Weather();
        // Conditions c = new Conditions();

        String weatherURL = UriComponentsBuilder
            .fromUriString(URL)
            .queryParam("q", city.replaceAll(" ", "+"))
            .queryParam("appid", apiKey)
            .toUriString();

        RequestEntity request = RequestEntity
            .get(weatherURL)
            .accept(MediaType.APPLICATION_JSON)
            .build();

        RestTemplate rTemplate = new RestTemplate();
        ResponseEntity<String> resp = rTemplate.exchange(request, String.class);
        JsonReader r = Json.createReader(new StringReader(resp.getBody()));
        JsonObject o = r.readObject();

        // String cityQuery = c.setCity(city);

        w.city = o.getString("name");
        JsonArray wArray = o.getJsonArray("weather");
        String description = "";
        String id = "";
        for (int i = 0; i < wArray.size(); i++) {
            JsonObject obj = wArray.getJsonObject(i);
            String tempDescription = "%s - %s".formatted(obj.getString("main"), obj.getString("description"));
            // String tempId = obj.getString("id");
            // String tempDescription = obj.getString("main");
            description = tempDescription;
            // id = tempId;
            // w.setDescription(tempDescription);
        }
        System.out.println(description);
        // System.out.println(id);
        // w.setId(id);
        w.setDescription(description);

        return Optional.of(w);
    }

    // public void insertCities (Weather weather) {
    //     // favRepo.insertFavorites(favorites);
    //     wRepo.insertCity(weather);
    // }

    // public List<Weather> getListOfCities() {
    //     // return favRepo.getUserFavoritesItems(email);
    //     return wRepo.getCities();
    // }

    // public void deleteCity(String city) {
    //     // favRepo.deleteFavoriteItemByUser(email,carparkNum);
    //     wRepo.deleteCity(city);
    // }



    // public Optional<Weather> getWeather(String city) {
    //     String weatherUrl = UriComponentsBuilder.fromUriString(URL)
    //         .queryParam("q", city.replaceAll(" ", "+")) //New York to New+York
    //         .queryParam("appid",apiKey)
    //         .toUriString();

    //     RequestEntity<Void> req = RequestEntity.get(weatherUrl)
    //     .accept(MediaType.APPLICATION_JSON)
    //     .build();

    //     RestTemplate template = new RestTemplate();

    //     ResponseEntity<String> resp = null;

    //     try {
    //         resp = template.exchange(req, String.class); //Execute the request specified in the given RequestEntity and return the response as ResponseEntity.
    //         Weather w = Weather.create(resp.getBody());
    //         System.out.println(">>>>>>>");
    //         return Optional.of(w);

    //     } catch (Exception e) {
    //         //TO DO: handle exception
    //         e.printStackTrace();
    //     }

    //     return Optional.empty();
    // }
}

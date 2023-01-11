package com.example.retestws78weatherserver.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.example.retestws78weatherserver.model.Weather;

@Repository
public class WeatherRepo {
    
    @Autowired
    private JdbcTemplate temp;

    String id = UUID.randomUUID().toString().substring(0,4);

    public boolean insertCity(Weather weather) {
        int updated = temp.update("insert into weather(id, city) values(?,?)", id, weather.getCity());
        return 1 == updated;
    }

    public List<Weather> getCities () {
        List<Weather> favItems = new LinkedList<>();
        SqlRowSet rs = temp.queryForRowSet("select city from weather");
        while (rs.next()) {
            Weather favItem = Weather.createRs(rs);
            favItems.add(favItem);
        }
        return favItems;
    }

    public boolean deleteCity(String city) {
        int updated = temp.update("delete from weather where city = ?", city);
        return 1 == updated;
    }

    
}

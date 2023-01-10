package com.example.retestws78weatherserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RetestWs78WeatherServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetestWs78WeatherServerApplication.class, args);
	}

}

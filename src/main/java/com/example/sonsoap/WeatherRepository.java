package com.example.sonsoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherRepository {
    private static final Map<String, WeatherData> weatherDataMap = new HashMap<>();

    @Autowired
    private RestTemplate restTemplate;

    private final String apiKey = "38284db17a2f09c11f2f65690f820a86";


    private final String weatherApiUrl = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&units=metric";

    @PostConstruct
    public void initData() {
    }

    public WeatherData findWeather(String city) {
        Assert.notNull(city, "City name must not be null");

        String url = UriComponentsBuilder.fromHttpUrl(weatherApiUrl)
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .toUriString();

        return restTemplate.getForObject(url, WeatherData.class);
    }
}

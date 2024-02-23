package com.example.sonsoap;

import mypackage.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {


    private final String apiKey = "38284db17a2f09c11f2f65690f820a86";

    private final String weatherApiUrl = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&units=metric";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherData getWeather(String city) {
        String url = weatherApiUrl.replace("{city}", city).replace("{apiKey}", apiKey);
        return restTemplate.getForObject(url, WeatherData.class);
    }
}

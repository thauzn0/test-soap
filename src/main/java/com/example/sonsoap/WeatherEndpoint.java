package com.example.sonsoap;



import mypackage.GetWeatherRequest;
import mypackage.GetWeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;



@Endpoint
public class WeatherEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private WeatherRepository weatherRepository;

    @Autowired
    public WeatherEndpoint(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getWeatherRequest")
    @ResponsePayload
    public GetWeatherResponse getWeather(@RequestPayload GetWeatherRequest request) {
        GetWeatherResponse response = new GetWeatherResponse();
        response.setWeatherData(weatherRepository.findWeather(request.getCity()));
        return response;
    }
}


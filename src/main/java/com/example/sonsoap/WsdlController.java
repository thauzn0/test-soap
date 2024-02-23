package com.example.sonsoap;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class WsdlController {

    @GetMapping("/ws/weather.wsdl")
    @ResponseBody
    public ClassPathResource getWeatherWsdl() {
        return new ClassPathResource("weather.wsdl");
    }
}

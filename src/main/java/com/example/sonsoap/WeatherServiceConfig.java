package com.example.sonsoap;

import com.example.sonsoap.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WeatherServiceConfig extends WsConfigurerAdapter {

    // RestTemplate bean'i
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public WeatherService weatherService(RestTemplate restTemplate) {
        return new WeatherService(restTemplate);
    }


    @Bean
    public DefaultWsdl11Definition weatherServiceDefinition(XsdSchema weatherSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("WeatherServicePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://example.com/weather");
        wsdl11Definition.setSchema(weatherSchema);
        return wsdl11Definition;
    }


    @Bean
    public XsdSchema weatherSchema() {
        return new SimpleXsdSchema(new ClassPathResource("weather.xsd"));
    }


    @Override
    public void addInterceptors(java.util.List<EndpointInterceptor> interceptors) {
        interceptors.add(new PayloadLoggingInterceptor());
    }
}

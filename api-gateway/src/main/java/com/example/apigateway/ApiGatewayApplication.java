package com.example.apigateway;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.RequestInterceptor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class ApiGatewayApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiGatewayApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Configuration
    static class OktaOAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .authorizeRequests().anyRequest().authenticated()
                    .and()
                    .oauth2Login()
                    .and()
                    .oauth2ResourceServer().jwt();
            // @formatter:on
        }
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    @Bean
    public RequestInterceptor getUserFeignClientInterceptor(OAuth2AuthorizedClientService clientService) {
        return new UserFeignClientInterceptor(clientService);
    }

    @Bean
    public AuthorizationHeaderFilter authHeaderFilter(OAuth2AuthorizedClientService clientService) {
        return new AuthorizationHeaderFilter(clientService);
    }
}

@Data
class Car {
    private String name;
    private String price;
    private String launchDate;
}

@FeignClient("car-service")
interface CarClient {

    @GetMapping("/cars")
    @CrossOrigin
    CollectionModel<Car> readCars();
}

@RestController
class CoolCarController {

    private final CarClient carClient;

    public CoolCarController(CarClient carClient) {
        this.carClient = carClient;
    }

    private Collection<Car> fallback() {
        return new ArrayList<>();
    }

    private Collection<Car> fallbackforluxurious() {
        ArrayList<Car> luxuriousCarList = new ArrayList<>();
        Car car = new Car();
        car.setName("AMC Gremlin");
        car.setPrice("90 lacs");
        car.setLaunchDate("20-June-2020");
        Car car1 = new Car();
        car1.setName("Ferrari");
        car1.setPrice("90 lacs");
        car1.setLaunchDate("20-June-2019");
        Car car2 = new Car();
        car2.setName("Jagaur");
        car2.setPrice("95 lacs");
        car2.setLaunchDate("20-May-2020");
        Car car3 = new Car();
        car3.setName("Porsche");
        car3.setPrice("90 lacs");
        car3.setLaunchDate("30-June-2020");
        Car car4 = new Car();
        car4.setName("Triumph Stag");
        car4.setPrice("95 lacs");
        car4.setLaunchDate("20-March-2020");

        luxuriousCarList.add(car);
        luxuriousCarList.add(car1);
        luxuriousCarList.add(car2);
        luxuriousCarList.add(car3);

        return luxuriousCarList;

    }

    private Collection<Car> fallbacknewlaunched() {
        ArrayList<Car> newLaunchedCarList = new ArrayList<>();
        Car car = new Car();
        car.setName("Ford Pinto");
        car.setPrice("60 lacs");
        car.setLaunchDate("28-July-2020");
        Car car1 = new Car();
        car1.setName("Yugo GV");
        car1.setPrice("60 lacs");
        car1.setLaunchDate("30-Aug-2020");
        Car car2 = new Car();
        car2.setName("BMW");
        car2.setPrice("60 lacs");
        car2.setLaunchDate("20-Sep-2020");
        Car car3 = new Car();
        car3.setName("Audi");
        car3.setPrice("60 lacs");
        car3.setLaunchDate("20-June-2020");
        Car car4 = new Car();
        car4.setName("AMC Gremlin");
        car4.setPrice("90 lacs");
        car4.setLaunchDate("20-June-2020");
        Car car5 = new Car();
        car5.setName("Jagaur");
        car5.setPrice("95 lacs");
        car5.setLaunchDate("20-May-2020");
        Car car6 = new Car();
        car6.setName("Porsche");
        car6.setPrice("90 lacs");
        car6.setLaunchDate("30-June-2020");
        Car car7 = new Car();
        car7.setName("Triumph Stag");
        car7.setPrice("95 lacs");
        car7.setLaunchDate("20-March-2020");

        newLaunchedCarList.add(car);
        newLaunchedCarList.add(car1);
        newLaunchedCarList.add(car2);
        newLaunchedCarList.add(car3);
        newLaunchedCarList.add(car4);
        newLaunchedCarList.add(car5);
        newLaunchedCarList.add(car6);
        newLaunchedCarList.add(car7);

        return newLaunchedCarList;

    }

    private Collection<Car> fallbackmostdemanded() {
        ArrayList<Car> mostDemandedCarList = new ArrayList<>();
        Car car1 = new Car();
        car1.setName("Yugo GV");
        car1.setPrice("60 lacs");
        car1.setLaunchDate("30-Aug-2020");
        Car car2 = new Car();
        car2.setName("BMW");
        car2.setPrice("60 lacs");
        car2.setLaunchDate("20-Sep-2020");
        Car car3 = new Car();
        car3.setName("Audi");
        car3.setPrice("60 lacs");
        car3.setLaunchDate("20-June-2020");
        Car car4 = new Car();
        car4.setName("AMC Gremlin");
        car4.setPrice("90 lacs");
        car4.setLaunchDate("20-June-2020");
        Car car5 = new Car();
        car5.setName("Ferrari");
        car5.setPrice("95 lacs");
        car5.setLaunchDate("20-June-2019");
        Car car6 = new Car();
        car6.setName("Bugatti");
        car6.setPrice("95 lacs");
        car6.setLaunchDate("20-May-2019");
        Car car7 = new Car();
        car7.setName("Triumph Stag");
        car7.setPrice("95 lacs");
        car7.setLaunchDate("20-March-2020");

        mostDemandedCarList.add(car1);
        mostDemandedCarList.add(car2);
        mostDemandedCarList.add(car3);
        mostDemandedCarList.add(car4);
        mostDemandedCarList.add(car5);
        mostDemandedCarList.add(car6);
        mostDemandedCarList.add(car7);

        return mostDemandedCarList;

    }

    @GetMapping("/luxurious-cars")
    @CrossOrigin
    @HystrixCommand(fallbackMethod = "fallbackforluxurious")
    public Collection<Car> luxuriousCars() {
        return carClient.readCars()
                .getContent()
                .stream()
                .filter(this::isLuxurious)
                .collect(Collectors.toList());
    }
    @GetMapping("/newly-launched-cars")
    @CrossOrigin
    @HystrixCommand(fallbackMethod = "fallbacknewlaunched")
    public Collection<Car> newlyLaunchedCars() {
        return carClient.readCars()
                .getContent()
                .stream()
                .filter(this::newlyLaunchedCars)
                .collect(Collectors.toList());
    }
    @GetMapping("/most-demanded-cars")
    @CrossOrigin
    @HystrixCommand(fallbackMethod = "fallbackmostdemanded")
    public Collection<Car> mostDemandedCars() {
        return carClient.readCars()
                .getContent()
                .stream()
                .filter(this::mostDemanded)
                .collect(Collectors.toList());
    }
    private boolean mostDemanded(Car car) {
        return !car.getName().equals("Porsche") &&
                !car.getName().equals("Lamborghini") &&
                !car.getName().equals("Ford Pinto") &&
                !car.getName().equals("Jagaur");
    }
    private boolean newlyLaunchedCars(Car car) {
        return !car.getLaunchDate().equals("20-June-2019") &&
                !car.getLaunchDate().equals("20-June-2018") &&
                !car.getLaunchDate().equals("20-May-2019");
    }
    private boolean isLuxurious(Car car) {
        return !car.getPrice().equals("60 lacs") &&
                !car.getPrice().equals("75 lacs");
    }
}

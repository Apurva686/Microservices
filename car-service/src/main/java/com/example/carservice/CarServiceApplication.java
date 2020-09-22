package com.example.carservice;

import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@EnableDiscoveryClient
@SpringBootApplication
public class CarServiceApplication extends SpringBootServletInitializer {
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CarServiceApplication.class);
    }
 
    public static void main(String[] args) {
        SpringApplication.run(CarServiceApplication.class, args);
    }

    @Configuration
    static class OktaOAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                .authorizeRequests().anyRequest().authenticated()
                    .and()
                .oauth2ResourceServer().jwt();
            // @formatter:on
        }
    }

    @Bean
    ApplicationRunner init(CarRepository repository) {
        return args -> {
          
                Car car = new Car("AMC Gremlin","90 lacs","20-June-2020");
                repository.save(car);
                Car car1 = new Car("Ferrari","90 lacs","20-June-2019");
                repository.save(car1);
                Car car2 = new Car("Jagaur","95 lacs","20-May-2020");
                repository.save(car2);
                Car car3 = new Car("Porsche","90 lacs","30-June-2020");
                repository.save(car3);
                Car car4 = new Car("Lamborghini","75 lacs","20-June-2018");
                repository.save(car4);
                Car car5 = new Car("Bugatti","95 lacs","20-May-2019");
                repository.save(car5);
                Car car6 = new Car("Triumph Stag","95 lacs","20-March-2020");
                repository.save(car6);
                Car car7 = new Car("Ford Pinto","60 lacs","28-July-2020");
                repository.save(car7);
                Car car8 = new Car("Yugo GV","60 lacs","30-Aug-2020");
                repository.save(car8);
                Car car9 = new Car("BMW","60 lacs","20-Sep-2020");
                repository.save(car9);
                Car car10 = new Car("Audi","60 lacs","20-June-2020");
                repository.save(car10);
     
           
            repository.findAll().forEach(System.out::println);
            
        };
    }
}

@Data
@NoArgsConstructor
@Entity
class Car {

    public Car(String name,String price,String launchDate) {
        this.name = name;
        this.price = price;
        this.launchDate = launchDate;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;
    
    @NonNull
    private String price;
    
    @NonNull
    private String launchDate;

    
}

@RepositoryRestResource
interface CarRepository extends JpaRepository<Car, Long> {
}
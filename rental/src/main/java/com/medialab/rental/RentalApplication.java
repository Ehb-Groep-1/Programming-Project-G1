package com.medialab.rental;

import com.medialab.rental.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.medialab.rental.repository")
//@RestController

public class RentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentalApplication.class, args);
    }

//    @GetMapping("/api/hello")
//    public Map<String, String> indexApi() {
//        HashMap<String, String> response = new HashMap<>();
//        response.put("message", "Application is running");
//        return response;
//    }

}

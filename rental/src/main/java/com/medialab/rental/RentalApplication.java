package com.medialab.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableJpaRepositories(basePackages = "com.medialab.rental.repository")

public class RentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentalApplication.class, args);
    }

    @GetMapping("/api/hello")
    public Map<String, String> indexApi() {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Application is running");
        return response;
    }

    @PostMapping("/api/login")
    public Map<String, String> loginUser(@RequestBody Map<String, String> response) {
        System.out.println(response);
        return response;
    }
}

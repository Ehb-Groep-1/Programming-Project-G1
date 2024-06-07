package com.medialab.rental.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.SecureRandom;

import static java.util.Arrays.asList;

@EnableWebSecurity(debug = true)
@Configuration
public class ConfiguratieApplicatie implements WebMvcConfigurer {
    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers(HttpMethod.POST, "/api/login", "/api/register","/*").permitAll()
                .requestMatchers(HttpMethod.GET,"/","/*","/register.html","/api/userinfo",
                        "/CSS/**", "/JAVASCRIPT/**","/PNG-JPG/**").permitAll()
                .anyRequest().authenticated()
                )
                .formLogin((form) ->
                        form
                            .loginPage("/login.html")
                            .loginProcessingUrl("/api/login")
                            .passwordParameter("password")
                            .usernameParameter("username")
                            .failureForwardUrl("/unknown_user.html")
                            .defaultSuccessUrl("/success.html")
                            .permitAll()
                        )
                .logout((logout) ->
                        logout
                                .logoutSuccessUrl("/")
                                .clearAuthentication(true)
                                .logoutUrl("/api/logout")
                                .permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(asList("http://127.0.0.1:5500","http://localhost:8080"));
        configuration.setAllowedMethods(asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH","OPTION"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(asList("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

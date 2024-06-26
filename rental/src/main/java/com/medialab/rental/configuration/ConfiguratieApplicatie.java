package com.medialab.rental.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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

@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
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
                                .requestMatchers(HttpMethod.POST, "/api/login", "/api/register","/login.html","/unknown_user.html").permitAll()
                .requestMatchers(HttpMethod.GET,"/","/*","/register.html","/api/userinfo",
                        "/CSS/**", "/JAVASCRIPT/**","/PNG-JPG/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/Admin/*","/item").hasAuthority("admin")
                .requestMatchers(HttpMethod.GET, "/Admin/*").hasAuthority("admin")
                .anyRequest().authenticated()
                )
                .formLogin((form) ->
                        form
                            .loginPage("/login.html")
                            .loginProcessingUrl("/api/login")
                            .passwordParameter("password")
                            .usernameParameter("username")
                            .failureForwardUrl("/unknown_user.html")
                            .failureUrl("/unknown_user.html")
                            .defaultSuccessUrl("/User/userInterface.html")
                            .permitAll()
                        )
                .logout((logout) ->
                        logout
                                .logoutSuccessUrl("/login.html")
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
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

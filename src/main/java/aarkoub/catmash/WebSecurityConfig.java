package aarkoub.catmash;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> {
            var cors = new CorsConfiguration();
            cors.setAllowCredentials(true);
            cors.setAllowedOrigins(List.of("http://localhost:3000", "https://catmash-for-latelier-cli.herokuapp.com"));
            cors.setAllowedMethods(List.of("GET","POST"));
            cors.setAllowedHeaders(List.of("*"));
            return cors;
        }).and().csrf().disable();
    }
}

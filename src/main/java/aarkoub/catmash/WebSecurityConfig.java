package aarkoub.catmash;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {




    public class SameSiteFilter extends GenericFilterBean {
        @Override
        public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setHeader("Set-Cookie", "HttpOnly; SameSite=strict");
                    chain.doFilter(request, response);
        }
    }



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

        http.addFilterAfter(new SameSiteFilter(), BasicAuthenticationFilter.class);
    }
}
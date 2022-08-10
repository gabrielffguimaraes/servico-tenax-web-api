package com.tenax.servico.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsFilterConfig  {
    @Value("${settings.cors_origin_localhost}")
    String localhost;

    @Value("${settings.cors_origin_git}")
    String git;

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean(){

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        List<String> All = Arrays.asList("*");
        corsConfiguration.setAllowedOrigins(Arrays.asList(localhost,git));
        corsConfiguration.setAllowedHeaders(All);
        corsConfiguration.setAllowedMethods(All);
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        CorsFilter corsFilter = new CorsFilter(source);
        FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>(corsFilter);
        filter.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return filter;
    }
}

package com.keycloak.saml.demo;

import org.keycloak.adapters.saml.servlet.SamlFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements ResourceLoaderAware{
    private ResourceLoader resourceLoader;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Resources controlled by Spring Security, which
        // adds "Cache-Control: must-revalidate".
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(-1);
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:keycloak-saml.xml");
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter());
        registration.setOrder(0);
        registration.addUrlPatterns("/admin/*");
        registration.addInitParameter("keycloak.config.file", resource.getFile().getAbsolutePath());
        registration.setName("Keycloak Filter");
        return registration;
    }

    public SamlFilter filter(){
        SamlFilter filter = new SamlFilter();
        return filter;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}

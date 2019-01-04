package com.github.sbouclier.stockmarketmicroservices.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.github.sbouclier.stockmarketmicroservices.web.filter.Slf4jMDCInsertingServletFilter;

@Component
public class FilterRegistrationConfig {

    @Bean
    public FilterRegistrationBean<Slf4jMDCInsertingServletFilter> slf4jDCFilter() {
        FilterRegistrationBean<Slf4jMDCInsertingServletFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new Slf4jMDCInsertingServletFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}

package br.com.springboot.essentials.adapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Murilo Victor on 15/06/2019
 */
@Configuration
public class WebConfigAdapter implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        PageableHandlerMethodArgumentResolver argumentResolver = new PageableHandlerMethodArgumentResolver();
        argumentResolver.setFallbackPageable(PageRequest.of(1,5));
        resolvers.add(argumentResolver);
    }
}

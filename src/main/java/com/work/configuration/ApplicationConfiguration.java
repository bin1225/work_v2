package com.work.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.work.common.argumentResolver.UserResolver;
import com.work.common.interceptor.LoginInterceptor;

@Configuration
@Import({DbConfiguration.class})
@PropertySource("classpath:application.properties")
@ComponentScan({"com.**.controller", "com.**.service", "com.work.common.**"})
@EnableWebMvc
public class ApplicationConfiguration implements WebMvcConfigurer {

	@Autowired
	private LoginInterceptor commonInterceptor;

	@Autowired
	private UserResolver commonResolver;

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonInterceptor)
			.addPathPatterns("/**/*")
			.excludePathPatterns("/user/login");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
		resolvers.add(commonResolver);
	}
}
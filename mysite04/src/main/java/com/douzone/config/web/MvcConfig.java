package com.douzone.config.web;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc	//Mvc, handlermapping, ViewResolver 오버라이딩으로 사용 가능
public class MvcConfig extends WebMvcConfigurerAdapter {

	//View Resolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		return null;
	}

	//Message Converter
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
		
//		List<MediaType> list = new ArrayList<MediaType>();
		messageConverter.setSupportedMediaTypes(
					Arrays.asList(new MediaType("text", "html", Charset.forName("UTF-8")))
				);
		
		return messageConverter;
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(
					Arrays.asList(new MediaType("application", "json", Charset.forName("UTF-8")))
				);
		
		return mappingJackson2HttpMessageConverter;
	}
	
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringHttpMessageConverter());
		converters.add(mappingJackson2HttpMessageConverter());
		
	}
	
	
	
	
}


/*
	<!-- ViewResolver -->
	<bean id="viewResolver"	class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="viewClass"
			value="org.springframework.web.servlet.view.JstlView" />
		<property name="prefix" value="/WEB-INF/views/" />
		<property name="suffix" value=".jsp" />
	</bean>
*/

/*
	<bean class="org.springframework.http.converter.StringHttpMessageConverter">
	<property name="supportedMediaTypes">
		<list>
			<value>text/html; charset=UTF-8</value>
		</list>
	</property>
	</bean>
	<bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
	<property name="supportedMediaTypes">
	   <list>
	        <value>application/json; charset=UTF-8</value>
	    </list>
	</property>
	</bean>
*/
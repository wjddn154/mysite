package com.douzone.mysite.initializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.douzone.mysite.config.AppConfig;
import com.douzone.mysite.config.WebConfig;

public class MySiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	

	/*
	 	<!-- Dispatcher Servlet -->
		<servlet>
			<servlet-name>spring</servlet-name>
			<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
			<init-param>
				<param-name>contextClass</param-name>
				<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
			</init-param>
			<init-param>
				<param-name>contextConfigLocation</param-name>
				<param-value>com.douzone.mysite.config.WebConfig</param-value>
			</init-param>
		</servlet>
		<servlet-mapping>
			<servlet-name>spring</servlet-name>
			<url-pattern>/</url-pattern>
		</servlet-mapping>	 
	 */
	
	
	
	/*
	 	<context-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>com.douzone.mysite.config.AppConfig</param-value>
		</context-param>
	
		<!-- ContextLoad Listener -->
		<listener>
			<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
		</listener> 
	 
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {AppConfig.class};
	}

	
	
	/*
	 	<context-param>
			<param-name>contextClass</param-name>
			<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
		</context-param>
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	
	

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	
	

	/*
	 	<!-- Encoding Filter -->
		<filter>
			<filter-name>encodingFilter</filter-name>
			<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
			<init-param>
				<param-name>encoding</param-name>
				<param-value>UTF-8</param-value>
			</init-param>
		</filter>
		<filter-mapping>
			<filter-name>encodingFilter</filter-name>
			<url-pattern>/*</url-pattern>
		</filter-mapping> 
	 */
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new CharacterEncodingFilter("UTF-8", false)};
	}



	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}
	
	
	
}

package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.web.MvcConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.douzone.mysite.controller", "com.douzone.mysite.exception"})
@Import({MvcConfig.class})
public class WebConfig {

}

/*
	<context:annotation-config />
	<context:component-scan	base-package="com.douzone.mysite.controller, com.douzone.mysite.exception" />
*/
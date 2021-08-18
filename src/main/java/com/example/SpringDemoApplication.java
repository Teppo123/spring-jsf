package com.example;

import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.faces.webapp.FacesServlet;

@EnableCaching
@SpringBootApplication
@ComponentScan//({ "com.example" })
public class SpringDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
		return new ServletRegistrationBean<>(new FacesServlet(), "*.faces");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(new Class[] { SpringDemoApplication.class, Initializer.class });
	}

	/*
	 * Blocks Tomcat-scanning error
	 */
	@Bean
	public TomcatServletWebServerFactory embeddedServletContainerFactory() {
		return new TomcatServletWebServerFactory();
	}

	static final class CustomTomcatEmbeddedServletContainerFactory extends TomcatServletWebServerFactory {
		@Override
		protected void postProcessContext(Context context) {
			((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
		}
	}

}

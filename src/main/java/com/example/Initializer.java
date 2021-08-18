package com.example;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.myfaces.ee.MyFacesContainerInitializer;
import org.apache.myfaces.webapp.StartupServletContextListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Initializer implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addListener(new StartupServletContextListener());
		Set<Class<?>> clazz = new HashSet<>();
		clazz.add(Initializer.class);

		MyFacesContainerInitializer facesInitializer = new MyFacesContainerInitializer();
		facesInitializer.onStartup(clazz, servletContext);
	}

}

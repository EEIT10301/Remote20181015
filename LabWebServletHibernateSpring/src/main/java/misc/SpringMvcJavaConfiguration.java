package misc;


import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

@Configuration 
@ComponentScan(basePackages={"controller"}) //<context:component-scan base-package="controller"></context:component-scan>
@EnableWebMvc //<mvc:annotation-driven></mvc:annotation-driven>
public class SpringMvcJavaConfiguration implements WebMvcConfigurer {
    @Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource rbms =new ResourceBundleMessageSource();
		rbms.setBasename("errors.message");
		return rbms;
	}
//	<bean id="messageSource"
//	class="org.springframework.context.support.ResourceBundleMessageSource">
//	<property name="basename" value="errors.message"></property>
//</bean>
	@Autowired
	private ServletContext application;

	public void configureViewResolvers(ViewResolverRegistry registry) {
		//System.out.println("haha");
		XmlViewResolver  xmlViewResolver =new XmlViewResolver();
		xmlViewResolver.setLocation(
				new ServletContextResource(application, "/WEB-INF/spring-views.xml"));
		registry.viewResolver(xmlViewResolver);
//	<bean
//		class="org.springframework.web.servlet.view.XmlViewResolver">
//		<property name="location" value="/WEB-INF/spring-views.xml"></property>
//	</bean>
		InternalResourceViewResolver  internalResourceViewResolver 
		=new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/pages/");
		internalResourceViewResolver.setSuffix(".jsp");
		registry.viewResolver(internalResourceViewResolver);
//		<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//		<property name="prefix" value="/WEB-INF/pages/" />
//		<property name="suffix" value=".jsp" />
//		<property name="order" value="10" />
//	</bean>
		

	}
}


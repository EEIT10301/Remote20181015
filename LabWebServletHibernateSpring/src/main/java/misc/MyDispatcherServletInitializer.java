package misc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("contexloderlistener");
		return new Class[] {SpringJavaConfiguration.class};
	}
//  <param-name>contextClass</param-name>
//  <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
//  <param-name>contextConfigLocation</param-name>
//  <param-value>misc.SpringJavaConfiguration</param-value>
// <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("DispatcherServletlistener");
		return new Class[] {SpringMvcJavaConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/","*.css","*.png","*.jpg","*.js"};
	}

//  <servlet>
//    <servlet-name>DispatcherServlet</servlet-name>
//    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
//    <init-param>
//    <param-name>contextClass</param-name>
//    <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
//    </init-param>
//    <init-param>
//    <param-name>contextConfigLocation</param-name>
//    <param-value>misc.SpringMvcJavaConfiguration</param-value>
//    </init-param>
//    <load-on-startup>1</load-on-startup>
//  </servlet>
//  <servlet-mapping>
//    <servlet-name>DispatcherServlet</servlet-name>
//    <url-pattern>/</url-pattern>
//  </servlet-mapping>
}

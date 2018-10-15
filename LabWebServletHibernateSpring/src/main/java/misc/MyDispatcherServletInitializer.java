package misc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("ContextLoaderListener");
		return new Class[] {SpringJavaConfiguration.class};
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("DispatcherServlet");
		return new Class[] {SpringMvcJavaConfiguration.class};
	}
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
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

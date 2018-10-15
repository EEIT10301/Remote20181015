package misc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(
		urlPatterns={"/*"}
)
public class EncodingFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		System.out.println("Servlet執行之前的前置作業");
		request.setCharacterEncoding("UTF-8");
//		if(前置作業：我不滿意) {
//			request.getRequestDispatcher("").forward(request, response);
//			return;
//		}
		
		chain.doFilter(request, response);			//呼叫Servlet
//		if(呼叫Servlet：我不滿意) {
//			request.getRequestDispatcher("").forward(request, response);
//			return;
//		}
		
		System.out.println("Servlet執行之後的後續功能");
//		if(後續功能：我不滿意) {
//			request.getRequestDispatcher("......").forward(request, response);
//			return;
//		}
	}

	private FilterConfig filterConfig;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	
	@Override
	public void destroy() {

	}
}

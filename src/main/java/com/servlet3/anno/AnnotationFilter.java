package com.servlet3.anno;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by tancw on 2016/5/20.
 */
@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "name", value = "filter's tanlan") },asyncSupported = true)
public class AnnotationFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig.getInitParameter("name"));
    }

	@Override
	public void destroy() {
        System.out.println("filter over");
    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("ip:"+ request.getRemoteHost());
//        System.out.println("port:"+ request.getRemotePort());
//        System.out.println("address:"+ request.getRemoteAddr());
        request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
    }
}

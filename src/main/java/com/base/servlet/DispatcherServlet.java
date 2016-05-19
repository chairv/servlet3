package com.base.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tancw on 2016/5/19.
 */
public class DispatcherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length(), uri.length() - 3);

		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) this.getServletContext().getAttribute("mapPath");
		if (map.containsKey(uri)) {
			// 能过http请求uri获得
			Object obj = map.get(uri);
			String methodName = request.getParameter("method");
			// 如果请求方法null,则默认调用Action对象中的index方法
			if (null == methodName) {
				methodName = "index";
			}
			Method method = null;
			try {
				method = obj.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			} catch (Exception e) {
				throw new RuntimeException(obj.getClass().getName() + "can't find method" + methodName);
			}

			try {
				method.invoke(obj, request, response);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doGet(req,resp);
	}
}

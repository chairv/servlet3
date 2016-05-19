package com.servlet3.anno;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tancw on 2016/5/19.
 */
@WebServlet(name = "AnnotationServlet", urlPatterns = "/AnnotationServlet")
public class AnnotationServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

	}
}

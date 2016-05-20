package com.servlet3.anno;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Created by tancw on 2016/5/20.
 */
@WebServlet("/upload")
@MultipartConfig(location = "F:/images/",
		fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class AnnotaionFileUpload extends HttpServlet {
	public AnnotaionFileUpload() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		// servlet3.0新引入的方法,用来处理Multipart/form-data类型编码的表单
//		Part part = request.getPart("file");
//		// 获取http头信息的headerInfo=(form-data;name="file",filename="文件名")
//		String headerInfo = part.getHeader("Content-disposition");
//		// 从http头信息中获取文件名filename=(文件名)
//		String fileName = headerInfo.substring(headerInfo.lastIndexOf("=") + 2, headerInfo.length() - 1);
//		// 获得存储上传文件的完整路径(文件名路径+文件名)
//		String fileSavingFolder = this.getServletContext().getRealPath("/Upload");
//		// 文件夹位置固定,文件夹采用与上传文件的原始名字相同
//		String fileSavingPath = fileSavingFolder + File.separator + fileName;
//		File f = new File(fileSavingFolder + File.separator);
//		if (!f.exists()) {
//			f.mkdirs();
//		}
//		// 将上传的文件内容写入服务器中
//		part.write(fileSavingPath);
//		out.println("文件上传成功");
//	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		// servlet3.0新引入的方法,用来处理Multipart/form-data类型编码的表单
		Part part = request.getPart("file");
		part.write(part.getSubmittedFileName());
	}
}

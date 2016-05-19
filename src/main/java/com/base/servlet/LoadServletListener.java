package com.base.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by tancw on 2016/5/19.
 */
public class LoadServletListener implements ServletContextListener {
	private static Map<String, Object> map = new HashMap<String, Object>();

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (null != map) {
			map = null;
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		String servletPackage = context.getInitParameter("servletPackage");
		String classPath = context.getRealPath("/WEB-INF/classes/" + servletPackage.replace(".", File.separator));
		scanClassPath(new File(classPath));
		context.setAttribute("mapPath", map);
		System.out.print(map);
	}

	/**
	 * 扫描类路径所有类文件,如果类文件含有Control注解，则把注解的value(URI)放进Map中作为key， 并将类的实例对象作为Map当中的value
	 * 
	 * @param file
	 */
	private void scanClassPath(File file) {
		try {
			if (file.isFile()) {
				if (file.getName().endsWith(".class")) {
					String path = file.getPath();
					MyClassLoader myClassLoader = new MyClassLoader(this.getClass().getClassLoader());
					Class<?> clazz = myClassLoader.load(path);
					Controller controller = clazz.getAnnotation(Controller.class);
					if (null != controller) {
						String uri = controller.value();
						Object action = clazz.newInstance();
						map.put(uri, action);
					}
				}
			} else {
				File[] files = file.listFiles();
				for (File child : files) {
					scanClassPath(child);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	class MyClassLoader extends ClassLoader {
		public MyClassLoader(ClassLoader parent) {
			super(parent);
		}

		public Class<?> load(String path) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(path);
				byte[] buf = new byte[fis.available()];
				int len = 0;
				int total = 0;
				int fileLength = buf.length;
				while (total < fileLength) {
					len = fis.read(buf, total, fileLength - total);
					total = total + len;
				}
				return super.defineClass(null, buf, 0, fileLength);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					fis = null;
				}
			}
		}
	}
}

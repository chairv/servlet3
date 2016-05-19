package com.servlet3.anno;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tancw on 2016/5/19.
 */
@WebServlet(name = "AnnotationServlet", urlPatterns = "/servlet", asyncSupported = true)
public class AnnotationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset-utf8");
        PrintWriter out = response.getWriter();
        out.print("下订单开始：" + new Date() + "<br/>");
        out.flush();

        AsyncContext ctx = request.startAsync();
        //异步执行开通订单
        new Thread(new CheckOrder(ctx)).start();
        out.print("订单成功" + new Date() + "<br/>");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private class CheckOrder implements Runnable {
        private AsyncContext ctx = null;

        public CheckOrder(AsyncContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                PrintWriter out = ctx.getResponse().getWriter();
                out.println("已经有权限了，let's go! : " + new Date() );
                out.flush();
                ctx.complete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

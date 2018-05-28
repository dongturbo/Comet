package com.comet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;


public class CometServlet extends HttpServlet {

    private static ConcurrentHashMap<String,Message> map=new ConcurrentHashMap<String,Message>();

    @Override
    public void init() throws ServletException {
        super.init();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        UUID uuid=UUID.randomUUID();
        resp.setContentType("text/html");
        HttpSession session =  req.getSession(true);
        session.setAttribute("uuid", uuid.toString());
        req.getRequestDispatcher("/test.jsp").forward(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public  ConcurrentHashMap<String, Message> getMap() {
        return map;
    }
}

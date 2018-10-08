package com.nm.namesearch.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.nm.booktitlerestapi.Restjsonresponsebooks;

public class Controller extends HttpServlet {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException {
        	
        	    response.setContentType("application/json");
                try {
                        String term = request.getParameter("term");
                        System.out.println("Data from ajax call " + term);
                        
                        Restjsonresponsebooks restjsonresponsebooks = new Restjsonresponsebooks();
                        
                        List list =
                        		restjsonresponsebooks.getjsonInventory(term);
                        
                        String searchList = new Gson().toJson(list);
                        System.out.println("searchList=="+ searchList);
                        response.getWriter().write(searchList);
                        
                } catch (Exception e) {
                        System.err.println(e.getMessage());
                }
        }
}
package com.higradius;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;
 

import com.higradius.Pojo.Pojo;

@WebServlet("/InvoiceEdit")
public class InvoiceEdit extends HttpServlet{
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceEdit() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				
				try {
					String dbDriver = "com.mysql.cj.jdbc.Driver"; 
			        String dbURL = "jdbc:mysql://localhost:3306/"; 
			        String dbName = "h2h_internship"; 
			        String dbUsername = "root"; 
			        String dbPassword = "root"; 
			        
			        Class.forName(dbDriver); 
			        Connection c = DriverManager.getConnection(dbURL + dbName, 
			                                                     dbUsername,  
			                                                     dbPassword); 
			        
			        
			        String id = request.getParameter("id");
			            
			        String sql;
			        sql = "UPDATE invoice_details SET total_open_amount = ?, notes = ? WHERE doc_id=?";
			           

			            
			        PreparedStatement stmt=c.prepareStatement(sql);
			            
			        stmt.setString(3,id);
			        stmt.setString(1,request.getParameter("amount"));
			        stmt.setString(2,request.getParameter("notes"));
			           
			        int rs = stmt.executeUpdate();

			            
				}
			            
			    catch (Exception e) {
			    	e.getMessage();
			    	System.out.println("Error " + e);
			    }

			    System.out.println("Data Edited");
    }
}

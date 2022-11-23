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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 


import com.google.gson.Gson;
import com.higradius.Pojo.Pojo;

/**
 * Servlet implementation class InvoiceViewcorrespondence
 */
@WebServlet("/InvoiceViewcorrespondence")
public class InvoiceViewcorrespondence extends HttpServlet{
	private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
      public InvoiceViewcorrespondence() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
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
		        sql = "SELECT doc_id, document_create_date, due_in_date, invoice_currency, total_open_amount FROM invoice_details WHERE doc_id=?";
		        PreparedStatement stmt=c.prepareStatement(sql);
	        	stmt.setString(1,id);
		        ResultSet rs = stmt.executeQuery();
		        
		        List<Pojo> result = new ArrayList<>();
		        
		        while(rs.next()) {
		        	Pojo pj = new Pojo();
		        	pj.setDocument_create_date(rs.getDate("document_create_date"));
		        	pj.setDue_in_date(rs.getDate("due_in_date"));
		        	pj.setInvoice_currency(rs.getString("invoice_currency"));
		        	pj.setTotal_open_amount(rs.getDouble("total_open_amount"));
		        	result.add(pj);
		        }
		        c.close();
		        stmt.close();
		        Gson gson = new Gson();
		        
		        String data = gson.toJson(result);
		        
		        out.print(data);
		        System.out.println(data);
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
		        out.flush();
			}
}
}
        

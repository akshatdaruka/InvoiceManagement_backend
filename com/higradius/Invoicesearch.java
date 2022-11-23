package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.higradius.Pojo.Pojo;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class Invoicesearch
 */
@WebServlet("/Invoicesearch")
public class Invoicesearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Invoicesearch() {
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
	        


	        String doc_id = request.getParameter("docid");
	        PreparedStatement stmt=c.prepareStatement ("select * from invoice_details where doc_id like ?");
	        
	        
	        stmt.setString(1, doc_id + "%");
	        ResultSet rs = stmt.executeQuery();
	        
	        List<Pojo> result = new ArrayList<>();
	        
	        while(rs.next()) {
	        	
	        	Pojo pj = new Pojo();
	        	pj.setName_customer(rs.getString("name_customer"));
	        	pj.setCust_number(rs.getString("cust_number"));
	        	pj.setInvoice_id(rs.getLong("doc_id"));
	        	pj.setTotal_open_amount(rs.getDouble("total_open_amount"));
	        	pj.setDue_in_date(rs.getDate("due_in_date"));
	            pj.setnotes(rs.getString("Notes"));
	        	result.add(pj);
	        }
	        c.close();
	        //stmt.close();
        Gson gson = new Gson();
	        //Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
	        String data = gson.toJson(result);
	        response.setContentType("application/json");
	        out.print(data);
	        System.out.println(data);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
	        out.flush();
		}

	}

}
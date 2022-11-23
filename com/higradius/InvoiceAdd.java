package com.higradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddInvoice
 */
@WebServlet("/InvoiceAdd")
public class InvoiceAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dbDriver = "com.mysql.cj.jdbc.Driver"; 
        String dbURL = "jdbc:mysql://localhost:3306/"; 
        String dbName = "h2h_internship"; 
        String dbUsername = "root"; 
        String dbPassword = "root"; 
        Connection c = null;
	
		try {
			Class.forName(dbDriver); 
	        c = DriverManager.getConnection(dbURL + dbName, 
	                                                     dbUsername,  
	                                                     dbPassword);
			String cname=request.getParameter("cname");
			String cnumber=request.getParameter("cnumber");
			String id=request.getParameter("id");
			String amt=request.getParameter("amt");
			String date=request.getParameter("date");
			String notes=request.getParameter("notes");
			String sql="Insert into invoice_details (name_customer, cust_number, doc_id, invoice_id, total_open_amount, due_in_date, notes) values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,cname);
			ps.setString(2,cnumber);
			ps.setString(3,id);
			ps.setString(4,id);
			ps.setString(5,amt);
			ps.setString(6,date);
			ps.setString(7,notes);
			ps.executeUpdate();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		finally {
			try {
				if(c!=null)
					c.close(); 
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class InvoiceServlet
 */
@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		int pageid=1,total=20;
//		String spageid=request.getParameter("page");  
//        if(spageid!=null) {
//        	pageid=Integer.parseInt(spageid);  
//            //total=5;  
//            if(pageid==1){}  
//            else{  
//                pageid=pageid-1;  
//                pageid=pageid*total+1;  
//            }  
        
        		
		try {			
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost:3306/h2h_internship" ;
			Connection con = DriverManager.getConnection(mysqlUrl, "root" , "root");
			String sql = "select name_customer,cust_number,invoice_id, total_open_amount,due_in_date,notes from invoice_details limit ?,20";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,Integer.parseInt(request.getParameter("page")));
			ResultSet rs = stmt.executeQuery();
			ArrayList<InvoiceData> invoiceList = new ArrayList<>();
				        
			while(rs.next()) {				
			InvoiceData resp = new InvoiceData();
			String cust_name = rs.getString("name_customer");
			String cust_no = rs.getString("cust_number");
			String inv_no = rs.getString("invoice_id");
			String amo = rs.getString("total_open_amount");
			String due_date = rs.getString("due_in_date");
			String note = rs.getString("notes");
			
			resp.setCust_name(cust_name);
			resp.setCust_no(cust_no);
			resp.setInv_no(inv_no);
			resp.setAmo(amo);
			resp.setDue_date(due_date);
			resp.setNote(note);
			invoiceList.add(resp);
			}
			
			Gson gson = new Gson();
			String data = gson.toJson(invoiceList);
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(data);
			out.flush();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void doPut() {
		
	}

}
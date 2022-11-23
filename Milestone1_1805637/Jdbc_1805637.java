package Milestone1_1805637;

import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Jdbc_1805637 
{
	private static BufferedReader lineReaders;

	public static void main(String[] args) throws NumberFormatException, IOException, ParseException,  Exception
	{	

		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/h2h_internship";
        String USERNAME = "root"; 
        String PASSWORD = "root";
    	
		
		int batchSize = 20;
		try 
		{
			Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
			con.setAutoCommit(false);
			
			String sql = "INSERT INTO invoice_details (business_code, cust_number, name_customer, clear_date, business_year, doc_id, posting_date, document_create_date, due_in_date, invoice_currency, document_type, posting_id, area_business, total_open_amount, baseline_create_date, cust_payment_terms, invoice_id, isOpen  ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	
	        lineReaders = new BufferedReader(new FileReader("1805637.csv"));
	        String lineText = null;
	
	        int count = 0;
	
	        lineReaders.readLine(); // skip header line
	        while ((lineText = lineReaders.readLine()) != null) 
	        {
	        	String[] data = lineText.split(",");
	        	Pojo_1805637 invobj= new Pojo_1805637();
	        	
	        	String business_code = data[0];
	        	invobj.setbusiness_code(business_code);
	        	
	        	String cust_number = data[1];
	        	invobj.setcust_number(cust_number);
	        	
	        	String name_customer = data[2];
	        	invobj.setname_customer(name_customer);
	        	
	        	String clear_date =data[3];
	        	invobj.setclear_date(clear_date);
	        	
	        	String business_year = data[4];
	        	invobj.setbusiness_year(business_year);
	        	
	        	String doc_id = data[5];
	        	invobj.setdoc_id(doc_id);
	        	
	        	String posting_date = data[6];
	        	invobj.setposting_date(posting_date);
	        	
	        	String document_create_date = data[8];
	        	invobj.setdocument_create_date(document_create_date);
	        	
	        	String due_in_date = data[9];
	        	invobj.setdue_in_date(due_in_date);
	        	
	        	String invoice_currency = data[10];
	        	invobj.setinvoice_currency(invoice_currency);
	        	
	        	String document_type = data[11];
	        	invobj.setdocument_type(document_type);
	        	
	        	String posting_id = data[12];
	        	invobj.setposting_id(posting_id);
	        	
	        	String area_business = data[13];
	        	invobj.setarea_business(area_business);
	        	
	        	String total_open_amount = data[14];
	        	invobj.settotal_open_amount(total_open_amount);
	        	
	        	String baseline_create_date = data[15];
	        	invobj.setbaseline_create_date(baseline_create_date);
	        	
	        	String cust_payment_terms = data[16];
	        	invobj.setcust_payment_terms(cust_payment_terms);
	        	
	        	String invoice_id = data[17];
	        	invobj.setinvoice_id(invoice_id);
	        	
	        	String isOpen = data[18];
	        	invobj.setisOpen(isOpen);
	        		        	
//	        	BUSINESS CODE
	        	pstmt.setString(1, invobj.getbusiness_code());
	        	
//	        	CUST NUMBER
	        	pstmt.setString(2, invobj.getcust_number());
	        	
//	        	CUSTOMER NAME
	        	pstmt.setString(3, invobj.getname_customer());
	        	
//	        	CLEAR DATE

	        	if(invobj.getclear_date().length()==0)
		          	   pstmt.setInt(4,0);
	        	else
	        	{
	        	java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(invobj.getclear_date());
	        	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	        	pstmt.setDate(4, sqlDate);
	        	}
        	        	
//	        	BUSINESS YEAR
	        	float year= Float.parseFloat(invobj.getbusiness_year());
	        	Integer intobject3 = new Integer((int) year);    
	            int iii = intobject3.intValue();           
	           
				pstmt.setInt(5, iii);
	        	
//	        	DOCID
	        	pstmt.setBigDecimal(6, new BigDecimal(invobj.getdoc_id()));
	        	
//	        	POSTING DATE 
	        	java.util.Date utilDate2 = new SimpleDateFormat("yyyy-MM-dd").parse(invobj.getposting_date());
	        	java.sql.Date sql_posting_Date = new java.sql.Date(utilDate2.getTime());
	        	pstmt.setDate(7, sql_posting_Date);
	            
//	        	DOCUMENT CREATE DATE
	        	java.util.Date utilDate3 = new SimpleDateFormat("yyyyMMdd").parse(invobj.getdocument_create_date());
	        	java.sql.Date sql_document_create_date = new java.sql.Date(utilDate3.getTime());
	        	pstmt.setDate(8, sql_document_create_date);
	        	 	
//	        	DUE DATE
	        	java.util.Date utilDate4 = new SimpleDateFormat("yyyyMMdd").parse(invobj.getdue_in_date());
	        	java.sql.Date sql_due_in_date = new java.sql.Date(utilDate4.getTime());
	        	pstmt.setDate(9, sql_due_in_date);
	        	
//	        	INVOICE CURRENCY
	        	pstmt.setString(10, invobj.getinvoice_currency());
	        	
//	        	DOCUMENT TYPE
	        	pstmt.setString(11, invobj.getdocument_type());
	        	
//	        	POSTING ID
	        	float post= Float.parseFloat(invobj.getposting_id());
	        	Integer intobject = new Integer((int) post);    
	            int i = intobject.intValue(); 
	            String s=String.valueOf(i);
	            byte posting_id_p=Byte.parseByte(s);
                pstmt.setByte(12, posting_id_p);
                
//              AREA BUSINESS
                pstmt.setString(13, invobj.getarea_business());
                
//              TOTAL OPEN AMOUNT
                double total_open_amount_p = Double.parseDouble(invobj.gettotal_open_amount());
                pstmt.setDouble(14, total_open_amount_p);
                
//              BASELINE CREATE DATE
                java.util.Date utilDate5 = new SimpleDateFormat("yyyyMMdd").parse(invobj.getbaseline_create_date());
	        	java.sql.Date sql_baseline_create_date = new java.sql.Date(utilDate5.getTime());
	        	pstmt.setDate(15, sql_baseline_create_date);
	        		        	
//	        	CUST PAYMENT TERMS
	        	pstmt.setString(16, invobj.getcust_payment_terms());
	        	
//	        	INVOICE ID
	        	if(invobj.getinvoice_id().length()==0)
	          	   pstmt.setLong(17,0);
	            else
	            {
	               float invoice= Float.parseFloat(invobj.getinvoice_id());
	               long invoice_id_p = (long) invoice;
	               pstmt.setLong(17,invoice_id_p);
	            }
	        	 
//	        	IS OPEN
	        	float post1= Float.parseFloat(invobj.getisOpen());
	        	Integer intobject1 = new Integer((int) post1);    
	            int ii = intobject1.intValue(); 
	            String s1=String.valueOf(ii);
	            byte isOpen_p=Byte.parseByte(s1);

	            
	            pstmt.setByte(18, isOpen_p);                
                pstmt.addBatch();
                count++;                
                if (count % batchSize == 0) 
                {
                    pstmt.executeBatch();
                }
            }
     
            // execute the remaining queries
	        pstmt.executeBatch();          
             
            con.commit();
            con.close();            
        } 
		catch (SQLException ex) 
		{        
            ex.printStackTrace();            
        }     
	}
}

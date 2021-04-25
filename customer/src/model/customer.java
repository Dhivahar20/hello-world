package model;

import java.sql.*;

public class customer { //A common method to connect to the DB
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer", "root", "8080"); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	public String insertcustomer(String CustomerID, String phone, String product, String payment) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into customer (`ID`,`CustomerID`,`phone`,`product`,`payment`)"
	 + " values (?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, CustomerID); 
	 preparedStmt.setString(3, phone); 
	 preparedStmt.setString(4, product); 
	 preparedStmt.setString(5, payment); 
	 
	// execute the statement
	
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the customer."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String readcustomer() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th> CustomerID</th><th>phone</th>" +
	 "<th>product</th>" + 
	 "<th>payment</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from customer"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id = Integer.toString(rs.getInt("ID"));
	 String customerID = rs.getString("CustomerID");
	 String Phone = rs.getString("phone"); 
	 String Product = rs.getString("product"); 
	 String Payment = rs.getString("payment"); 
	 // Add into the html table
	 output += "<tr><td>" + customerID + "</td>"; 
	 output += "<td>" + Phone + "</td>"; 
	 output += "<td>" + Product + "</td>"; 
	 output += "<td>" + Payment + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'  class='btn btn-secondary'></td>"
	 + "<td><form method='post' action=''>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" +id 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String updatecustomer(String id , String CustomerID, String phone, String product, String payment)
	
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE customer  SET customerID=?,phone=?,product=?,payment=? WHERE ID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, CustomerID); 
	 preparedStmt.setString(2, phone);  
	 preparedStmt.setString(3, product); 
	 preparedStmt.setString(4, payment);
	 preparedStmt.setInt(5, Integer.parseInt(id));
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the customer."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String deleteItem(String id) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from customer where ID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(id)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	}

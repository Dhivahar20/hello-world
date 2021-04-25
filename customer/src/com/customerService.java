package com;

import model.customer;
 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/customer") 
public class customerService 
{ 
	 customer  customerObj = new  customer(); 
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readcustomer() 
{ 
	return customerObj.readcustomer();
} 

@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertItem(@FormParam("CustomerID") String CustomerID, 
 @FormParam("phone") String phone, 
 @FormParam("product") String product, 
 @FormParam("payment") String payment) 
{ 
 String output = customerObj.insertcustomer(CustomerID, phone, product, payment); 
return output; 
}
}

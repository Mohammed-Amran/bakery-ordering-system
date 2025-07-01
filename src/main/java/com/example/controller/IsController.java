package com.example.controller;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DAO.DaoOrders;
import com.example.model.Orders;

@Controller
public class IsController {

	
	    //This method below opens the 'owner.jsp' page:
		@GetMapping("/accessISLoginPage")
		protected String accessOwnerLoginPage() {
			
			return "view/ISLoginPage";
			
		}//closing brace of the 'accessOwnerPage()' method.
	
		
		
	
		@PostMapping("/loginIntoIS")
		protected String loginToIS(@RequestParam Map<String, String> input, HttpServletRequest req, Model model) {
			
			String destination = "";
			
			String email = input.get("email");
			String password = input.get("password");
			
			
			if("owner@gmail.com".equals(email) && "1122".equals(password)) {
				
				HttpSession session = req.getSession(true);
				
				session.setAttribute("email", email);
				session.setAttribute("password", password);
				
				destination = "view/IS";
				
			}
			else {
				
				model.addAttribute("loginErrorMessage", "Wrong email or password! please try again");
				
				destination = "view/ISLoginPage";
			}
			
			
			
			return destination;
			
		}//closing brace of the 'loginToIS()' method.
		
	
		@PostMapping("/retrieveSalesData")
		protected String retrieveSalesData(@RequestParam Map<String, String> input, HttpServletRequest req, Model model) {
			
			
			// 1st: Retrieving and validating the requested orderDate
		    String rawOrderDate = input.get("orderDate");
		    
		    // Validate input
		    if (rawOrderDate == null || rawOrderDate.isBlank() || rawOrderDate.equals("--")) {
		    	
		       
		        
		        
		        
		        return "view/IS";
		    }
		    
		    LocalDate requestOrderDate;
		    
		    try {
		    	
		        // First try to parse as ISO format (YYYY-MM-DD)
		        try {
		        	
		            requestOrderDate = LocalDate.parse(rawOrderDate);
		            
		        } 
		        catch (DateTimeParseException e1) {
		        	
		            // If ISO format fails, try DD-MM-YYYY format
		            try {
		            	
		                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		                
		                requestOrderDate = LocalDate.parse(rawOrderDate, formatter);
		                
		            }
		            catch (DateTimeParseException e2) {
		            	
		                model.addAttribute("dateError", "Invalid date format. Please use either DD-MM-YYYY or YYYY-MM-DD format");
		                
		                
		                
		                return "view/IS";
		                
		            }
		            
		            
		        }
		        
		        
		        // 2nd: Instantiating an object from the 'DaoOrders' class:
		        DaoOrders daoOrdersObject = new DaoOrders();
		        
		        
		        // 3rd: Calling the 'getAllOrdersForAnalytics()' method via the daoOrdersObject:
		        try {
		        	
		        	HttpSession session = req.getSession(true);
		        	
		            List<Orders> retrievedOrdersForAnalytics = daoOrdersObject.getAllOrdersForIS(requestOrderDate);
		            
		            session.setAttribute("ordersListForAnalytics", retrievedOrdersForAnalytics);
		            
		            
		        } 
		        catch (SQLException e) {

		        	e.printStackTrace();
		        }
		        
		    }
		    catch (Exception e) {
		    			        
		        
		        e.printStackTrace();
		    }
		    
	    
		    return "view/IS";
		}
		
		
	
	
	
}

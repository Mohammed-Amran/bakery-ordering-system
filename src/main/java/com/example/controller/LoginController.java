package com.example.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DAO.*;

import com.example.model.*;



@Controller
public class LoginController {

	
	
	//This method takes the user from 'viewerOnly' page to the 'login' page.
	@RequestMapping("/accessLoginPage")
	protected String accessLoginPage() {
		
		return "view/login";
		
	} //closing brace of the 'accessLoginPage()' method.
	
//##########################################################################################	
	
	@GetMapping("/forwardToCustomerPage")
	public String showCustomerPage(HttpServletRequest request) {
		    
			
		    return "view/customer";
		}
	
	
//##############################################################################################	
	
	
	//This Method logs in the user from the 'login' page into the 'customer' page
	@PostMapping("/logIn")
	protected String loginController(@RequestParam Map<String,String> req, Model model, HttpServletRequest request) {
		
		    String destination = "";
		
		    
			//Retrieving the inputed field values from the login page via 'req' parameter
			String email = req.get("email");
			String password = req.get("password");
			
			
			//Instantiating an object from the 'DaoUsers' class - in order to access the 'users' table.
			DaoUsers daoObj = new DaoUsers();
			
			
			//checking if the user has already registered or not! via 'CheckUser()' method.
			if(daoObj.CheckUser(email, password)) {
				
				
				/*So, if the result was True - this part will run
	            And it means that the user has Registered already: */
		    	
				
		    	//Retrieving the users fullName from the 'users' table in the DB:
		    	String fullName = daoObj.retrieveFullName(email, password);
		    	
		    	
		    	//Retrieving the users phoneNo from the 'users' table in the DB:
		    	String phoneNo = daoObj.retrievePhoneNo(email, password);
		    	
		    	
		    	//Retrieving the users ID from the 'users' table in the DB:
		    	int userId = daoObj.retrieveId(email);
		    	
		    	
		    	    	
		    	
		    	//Initializing a Session object:
		    	HttpSession session = request.getSession(true);
		    	    	
		    	
		    	/*Saving the essential user-info's to the session object!
		    	Because we would require them for further steps:*/
		    	
		    	session.setAttribute("fullName", fullName);
		    	session.setAttribute("userId", userId);
		    	session.setAttribute("email", email);
		    	session.setAttribute("password", password);
		    	session.setAttribute("phoneNo", phoneNo);
				
	    	
		    	try {
		    		    
		    		
		    	    //1st: Retrieving the items-number in the 'cartItems' table.	
		    		
		    		
		    		//I: Instantiating an object from the 'daoCart' class.
		    		DaoCart daoCartObj = new DaoCart();
		    		
		    		
		    		//II: getting the items-numbers via the 'getCartItemCount()' method.
					int itemsCount = daoCartObj.getCartItemCount(userId);
					
					
					//III: saving the itemsCount into session Scope.
					session.setAttribute("cartCounter", itemsCount); 
		    		
		    	
			//================================================================================================		
					
					
					
			  //2nd: Retrieving the items from the 'cartItems' table
					
					
					//I: getting the items for the specific userId via the 'getCartItemsByUserId()' method.
					List<CartItems> retrievedItems = daoCartObj.getCartItemsByUserId(userId);
			    	
					
					//II: saving the retrieved items into session scope.
			    	session.setAttribute("retrievedCartItems", retrievedItems);
					
		    		
			//================================================================================================
			    	
			    	
			  //3rd: Retrieving the bread items from the 'breads' tables.
			    	
			    	
			    	 //I: Instantiating an object from the 'DaoBreads' class.
		    		 DaoBreads breadsObj = new DaoBreads();
		        	
		    		 
		    		 //II: getting the bread items via the 'getBreads()' method.
		        	 List<Breads> retrievedBreads = breadsObj.getBreads();
		    			
		        	 //III: saving the retrieved breads into the session scope:
		        	 session.setAttribute("retrievedBreads", retrievedBreads);
		        	 
		        	 //IV: this allows the 'when' tag in the 'customer' page to loop through the 'retrievedBreads'
		        	 session.setAttribute("showCategory", "Breads");
		        	 
		        	 
		        	//Instantiating an object from the 'DaoOrders' class - in order to access the 'orders' table.
					DaoOrders ordersObj = new DaoOrders();
			        	 
			        List<Orders> retrievedItemsIntoInbox = ordersObj.getOrders(userId);
			     		
			     	session.setAttribute("retrievedOrderedItems", retrievedItemsIntoInbox);
			     		
			     		
			     	 int orderedItemsCounter = ordersObj.getOrderedItemsCount(userId);
			     	    
			     	 session.setAttribute("inboxCounter", orderedItemsCounter);
		        	 
		    		
			     	 //--------------Notifications------------------------------------------
			     	 
			     	 //I. Instantiating an object from the 'DaoNotifications' class:
			     	 DaoNotifications notiObj = new DaoNotifications();
			     	 
			     	 
			     	 //II. Calling the 'getUnReadNotifications()' method and saving into a list of type 'Notifications':
			     	 List<Notifications> unReadNotifications = notiObj.getUnReadNotifications(userId, false);
			     	 
			     	 session.setAttribute("unReadNotificationsList", unReadNotifications);
			     	 
			     	 
			     	 //III. Retrieve the 'unReadNotificationsCounter':
			     	 int unReadNotificationsCounter = notiObj.getUnreadNotificationsCount(userId, false);
			     	 
			     	 session.setAttribute("unReadNotificationsCounter", unReadNotificationsCounter);
			     	 
			     	 //--------------------------------------------------------------------
			     	 
		        	 destination = "redirect:/forwardToCustomerPage";
		        	 
		    		
				} 
		    	catch (Exception e) {
					
					System.out.print(e);
				}
		    	
               
		    	
		    	
			}
			else {
				
				
				//So, if the user hasn't registered - this Part will run
				
				model.addAttribute("loginError", "Login failed! Please check your email and password");
				
				model.addAttribute("email", email);
				
				
				destination = "view/login";
					
			}//closing brace of the 'else' part
			
		
		
			return destination;	
		
		
	
	}//closing brace of the 'loginController()' method.
	
	
	
	
//##############################################################################################	

	
	
	//When the user logs out! this method will take the user from 'customer' page back to the 'login' page
	@GetMapping("/TakeBackUserFromCustomerToLogin")
	public String showLoginPage(@RequestParam(value = "logOutMessage", required = false) String logOutMessage, Model model) {
	    
		
		if (logOutMessage != null) {
	       
			model.addAttribute("logOutMessage", logOutMessage);
	    }
		
	    return "view/login"; 
	    
	}//closing brace of the 'showLoginPage()' method.

	
	
//##############################################################################################	
	
	
	//This method returns back the user from 'login' page back to the 'viewerOnly' page.
	@GetMapping("/backToView")
	protected String backToView() {
		
		return "view/viewerOnly";
		
	}//closing brace of the 'backToView()' method.
	
	
	
}//closing brace of the class.

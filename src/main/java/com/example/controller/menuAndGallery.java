package com.example.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.DAO.DaoBreads;
import com.example.DAO.DaoDeserts;
import com.example.model.Breads;
import com.example.model.Deserts;

@Controller
public class menuAndGallery {

	
	//This Method takes the user from 'viewerOnly' page to the 'menuAndGallery' page.
	@GetMapping("/gotToMenuAndGalleryPage")
	protected String gotToMenuAndGalleryPage(HttpServletRequest req) {
		
		//Instantiating a session object
		HttpSession session = req.getSession();
		
		//I: Instantiating an object from the 'DaoBreads' class.
		DaoBreads breadsObj = new DaoBreads();
   	
		 
		try {
			
			//II: getting the bread items via the 'getBreads()' method.
	   	    List<Breads> retrievedBreads = breadsObj.getBreads();
				
	   	    //III: saving the retrieved breads into the session scope:
	   	    session.setAttribute("retrievedBreads", retrievedBreads);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		session.setAttribute("showCategory", "Breads");
		
		
		return "view/menuAndGallery";
		
	}//closing brace of the 'gotToMenuAndGalleryPage()' method.

	

//##############################################################################################	
	
	
	
	
	//This method shows either of breads or deserts items based on the chosen category.
	@RequestMapping("/categoryController")
	  protected String CategoryController(@RequestParam Map<String,String> input, Model model, HttpServletRequest request) {
		  
		  HttpSession session = request.getSession(true);
		
		  String category = input.get("category");
		  
		  if("Breads".equals(category)) {
			  
			//I: Instantiating an object from the 'DaoBreads' class.
			DaoBreads breadsObj = new DaoBreads();
			  
			  try {
					
					//II: getting the bread items via the 'getBreads()' method.
			   	    List<Breads> retrievedBreads = breadsObj.getBreads();
						
			   	    //III: saving the retrieved breads into the session scope:
			   	    session.setAttribute("retrievedBreads", retrievedBreads);
				} 
				catch (Exception e) {
					
					e.printStackTrace();
					
				}
			  
			  
		  }
		  else if("Cakes".equals(category)){
			  
			  
			//Instantiating an object from the 'DaoCakes' class - in order to access the 'deserts' table in the DB:
            DaoDeserts cakesObj = new DaoDeserts();
      		
            
            try {
				
            	  //Retrieving the desert items from the 'deserts' table via the 'getDeserts()' methods:
          		  List<Deserts> retrievedDeserts = cakesObj.getDesert();
          		
          		
          		  //Saving the retrieved desert items List into the session scope:
          		 session.setAttribute("retrievedDeserts" , retrievedDeserts);
            	
			}
            catch (Exception e) {
				
				e.printStackTrace();
			}
            
			  
		  }
		  
		  
		  
		  session.setAttribute("showCategory", category);
	
		  
		  return "view/menuAndGallery";
		  
	  }//closing brace of the 'CategoryController()' method.
	
	
	
	
//##############################################################################################	
	
	
	
	//This methods takes the user from 'menuAndGallery' page back to the 'viewerOnly' page. 
	@GetMapping("/getBackToViewPageFromMenuAndGallery")
	protected String getBackToViewOnlyPage() {
		
		return "view/viewerOnly";
		
	}//closing brace of the 'getBackToViewOnlyPage()' method.
	
	
	
	
	
	
	
	
}//closing brace of the class.

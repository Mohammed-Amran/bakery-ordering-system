package com.example.DAO;

import java.sql.*;

import com.example.model.Users;

public class DaoUsers {

	
//==================================================================================================================	

	//JDBC URL for MySQL connection
    private final String jdbcURL = "jdbc:mysql://localhost:3306/bakeryweb?useSSL=false&serverTimezone=UTC";
    private final String jdbcUsername = "root"; // Our MySQL username
    private final String jdbcPassword = "1234"; // Our MySQL password 

    
    // Method to get connection to MySQL Database
    private Connection getConnection() throws SQLException {
      
    	
    	try {
    		
    		//Step-1: Loading the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            
        } 
    	catch (ClassNotFoundException e) {
    		
            e.printStackTrace();
        }
    	
    
    	
    	       //Step2: Establishing a Connection
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        
             
    }//closing brace of the 'getConnection()' method.

    
//================================================================================================================== 
    
    
    
    
    //This method Inserts User into 'users' table
    public boolean insertUser(Users user) {
    	
    	
        String sql = "INSERT INTO users (fullName, email, pass, phoneNo) VALUES (?, ?, ?, ?)";

        try ( 
        	  Connection conn = getConnection(); 
        	
        	  //Step-3: Creating the preparedStatements:	
        	  PreparedStatement stmt = conn.prepareStatement(sql)
        			  
        	) {

            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getPhoneNo());

            System.out.println("Trying to insert: " + user.getFullName() + ", " 
                + user.getEmail() + ", " + user.getPassword() + ", " + user.getPhoneNo());

            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0; // true if at least one row inserted

        }       
        catch (SQLException e) {
        	
            e.printStackTrace();
            return false;
            
        }
        
        
        
        
    }//closing brace of the 'insertUser'.
	
	
//==================================================================================================================     
    
    
    //Check if a user exist in the db or not!? :
    //This is for the Logging in Authentication:
    public boolean CheckUser(String email, String password) {
    	
        String sql = "SELECT * FROM users WHERE email = ? AND pass = ?";
        
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();  // If there is a result, return true, else false
            }
            
        } 
        catch (SQLException e) {
        	
            e.printStackTrace();
            return false;
            
        }
        
    }//closing brace of the 'CheckUser()' method.

  //================================================================================================================== 
    
    
   
    //Retrieving the Fullname of user.
    public String retrieveFullName(String email, String pass) {
    	
    	
    	String sql = "SELECT fullName FROM users WHERE email = ? AND pass = ?";
    	
    	
    	try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, pass);

            try (ResultSet rs = stmt.executeQuery()) {
            	
                if (rs.next()) {
                	
                    return rs.getString("fullName");
                    
                }
                
            }

        } 
    	catch (SQLException e) {
    		
            e.printStackTrace();
        }
    	

        return "Unknown Name"; // return Unknown Name if not found or error occurs
    	
    	
 
    }//closing brace of the 'retrieveFullName()' method
    
    
   
//================================================================================================================== 
    
    
//Retrieving the PhoneNo of user.
public String retrievePhoneNo(String email, String pass) {
	
	
	String sql = "SELECT phoneNO FROM users WHERE email = ? AND pass = ?";
	
	try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, email);
        stmt.setString(2, pass);

        try (ResultSet rs = stmt.executeQuery()) {
        	
            if (rs.next()) {
            	
                return rs.getString("phoneNo");
                
            }
        }

    } 
	catch (SQLException e) {
		
        e.printStackTrace();
    }

    return "Unknown phoneNo"; // return Unknown phoneNo if not found or error occurs
	
	
	
}//closing brace of the 'retrievePhoneNo()' method.
    
    
    
//==================================================================================================================   



//Retrieving the id of user.
public int retrieveId(String email) {
	
	
	String sql = "SELECT userId FROM users WHERE email = ?";
	
	try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1, email);
     

      try (ResultSet rs = stmt.executeQuery()) {
      	
          if (rs.next()) {
          	
              return rs.getInt("userId");
              
          }
      }

  } 
	catch (SQLException e) {
		
      e.printStackTrace();
  }

  return -1; // return Unknown id, if id not found or error occurs
	
	
	
}//closing brace of the 'retrieveId()' method.
  
  
  
//==================================================================================================================
	




//============================- THIS METHODS BELOW ARE FOR UPDATING THE USERS TABLE FIELDS -========================

//Method to update the fullName of a user by their userId
public boolean updateUserFullName(int userId, String newFullName) {
	
   String sql = "UPDATE users SET fullName = ? WHERE userId = ?";
 
   try (Connection conn = getConnection();
        
		PreparedStatement stmt = conn.prepareStatement(sql)) {
     
        stmt.setString(1, newFullName);
        stmt.setInt(2, userId);
     
        int rowsAffected = stmt.executeUpdate();
     
     // Return true if exactly 1 row was updated
     return rowsAffected == 1;
     
    } 
    catch (SQLException e) {
    	
     e.printStackTrace();
     return false;
     
 }
   
}//closing brace of the 'updateUserFullName()' method



//- - - - - - - - - - - - - - - - - - - - - - - - - - - 


//Method to update the email of a user by their userId
public boolean updateUserEmail(int userId, String newEmail) {
	
 String sql = "UPDATE users SET email = ? WHERE userId = ?";

 try (Connection conn = getConnection();
      
		PreparedStatement stmt = conn.prepareStatement(sql)) {
   
      stmt.setString(1, newEmail);
      stmt.setInt(2, userId);
   
      int rowsAffected = stmt.executeUpdate();
   
   // Return true if exactly 1 row was updated
   return rowsAffected == 1;
   
  } 
  catch (SQLException e) {
  	
   e.printStackTrace();
   return false;
   
}
 
}//closing brace of the 'updateUserEmail()' method




//- - - - - - - - - - - - - - - - - - - - - - - - - - - 


//Method to update the password of a user by their userId
public boolean updateUserPassword(int userId, String newPassword) {
	
String sql = "UPDATE users SET pass = ? WHERE userId = ?";

try (Connection conn = getConnection();
    
		PreparedStatement stmt = conn.prepareStatement(sql)) {
 
    stmt.setString(1, newPassword);
    stmt.setInt(2, userId);
 
    int rowsAffected = stmt.executeUpdate();
 
 // Return true if exactly 1 row was updated
 return rowsAffected == 1;
 
} 
catch (SQLException e) {
	
 e.printStackTrace();
 return false;
 
}

}//closing brace of the 'updateUserPassword()' method




//- - - - - - - - - - - - - - - - - - - - - - - - - - - 


//Method to update the phoneNo of a user by their userId
public boolean updateUserphoneNo(int userId, String newPhoneNo) {
	
String sql = "UPDATE users SET phoneNo = ? WHERE userId = ?";

try (Connection conn = getConnection();
    
	 PreparedStatement stmt = conn.prepareStatement(sql)) {
 
    stmt.setString(1, newPhoneNo);
    stmt.setInt(2, userId);
 
    int rowsAffected = stmt.executeUpdate();
 
 // Return true if exactly 1 row was updated
 return rowsAffected == 1;
 
} 
catch (SQLException e) {
	
 e.printStackTrace();
 return false;
 
}

}//closing brace of the 'updateUserPhoneNo()' method


//================================================================================================================== 



//Retrieving the Fullname of user.
public String retrieveFullName(int userId) {
	
	
	String sql = "SELECT fullName FROM users WHERE userId = ?";
	
	
	try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

         stmt.setInt(1, userId);
       

        try (ResultSet rs = stmt.executeQuery()) {
        	
            if (rs.next()) {
            	
                return rs.getString("fullName");
                
                
            }
            
        }

    } 
	catch (SQLException e) {
		
        e.printStackTrace();
    }
	

    return "Unknown Name"; // return Unknown Name if not found or error occurs
	
	

}//closing brace of the 'retrieveFullName()' method.



//================================================================================================================== 


//Retrieving the email of user.
public String retrieveEmail(int userId) {
	
	
	String sql = "SELECT email FROM users WHERE userId = ?";
	
	
	try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

         stmt.setInt(1, userId);
     

      try (ResultSet rs = stmt.executeQuery()) {
      	
          if (rs.next()) {
          	
              return rs.getString("email");
              
          }
          
      }

  } 
	catch (SQLException e) {
		
      e.printStackTrace();
  }
	

  return "Unknown Email"; // return Unknown Name if not found or error occurs
	
	

}//closing brace of the 'retrieveEmail()' method.


//==================================================================================================================   


//Retrieving the password of user.
public String retrievePassword(int userId) {
	
	
	String sql = "SELECT pass FROM users WHERE userId = ?";
	
	
	try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

    stmt.setInt(1, userId);
   

    try (ResultSet rs = stmt.executeQuery()) {
    	
        if (rs.next()) {
        	
            return rs.getString("pass");
            
        }
        
    }

} 
	catch (SQLException e) {
		
    e.printStackTrace();
}
	

return "Unknown pass"; // return Unknown Name if not found or error occurs
	
	

}//closing brace of the 'retrievePassword()' method.



//==================================================================================================================   


//Retrieving the phoneNo of user.
public String retrievePhoneNo(int userId) {
	
	
	String sql = "SELECT phoneNo FROM users WHERE userId = ?";
	
	
	try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

         stmt.setInt(1, userId);
 

  try (ResultSet rs = stmt.executeQuery()) {
  	
      if (rs.next()) {
      	
          return rs.getString("phoneNo");
          
      }
      
  }

} 
	catch (SQLException e) {
		
  e.printStackTrace();
}
	

return "Unknown phoneNo"; // return Unknown Name if not found or error occurs
	
	

}//closing brace of the 'retrievePassword()' method.







	
}//closing curly brace of the class.

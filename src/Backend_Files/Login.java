package Backend_Files;

import java.sql.*;
public class Login{
	public String account_check(String username, String pass, int choice) {
                  String confirm;
		  try {
			   //int choice = 1;
			   //String name = "balabr2002@gmail.com";
			   //String pass = "123@435";
			   //connection to database
			   Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini-project", "root", "root");
			   //create statement 
			   //Statement myStmt = myConn.createStatement();
			   PreparedStatement Prestmt;
			   //prepare SQL statement
			   Prestmt = myConn.prepareStatement("select * from account where username = ?");
			   Prestmt.setString(1, username);
			   ResultSet myRs = Prestmt.executeQuery();
			   //resultset
                           if(myRs.next()){
                               if(pass.equals(myRs.getString("password")) && choice == myRs.getInt("choice")) {
				   confirm = myRs.getString("name");
                                   myConn.close();
                                   return confirm;
                                }
                                else {
                                        myConn.close();
                                        return "-1";
                                }
                           }
                           else{
                               return null;
                           }
		}
	    catch(SQLException ex) {
                System.out.println(ex);
                return "Failure";
	    }
	}
	
	public String account_insert(String name, String username, String pass, int choice) {
            if(choice == 1){
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mini-project", "root", "root");
			PreparedStatement Prestmt = myConn.prepareStatement("select * from account where username = ?");
			Prestmt.setString(1, username);
			ResultSet myRs = Prestmt.executeQuery();
			if(myRs.next()) {
				System.out.println("Account already exists");
			}
			else {
				Prestmt = myConn.prepareStatement("insert into account(username,password,choice,name) values(?,?,?,?)");
				Prestmt.setString(1,username);
				Prestmt.setString(2,pass);
				Prestmt.setInt(3,2);
				Prestmt.setString(4, name);
				Prestmt.executeUpdate();
			}
			myConn.close();
                        return "Success";
		}
		catch(SQLException exc){
                        System.out.println(exc);
			return "Failure";
		}
            }
            return "Failure";
	}
}
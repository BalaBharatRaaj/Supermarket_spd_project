package Backend_Files;
import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;

class Filehandling{
	public static void display3(String[] app, Connection myConn) {
		try {
			PreparedStatement Prestmt = myConn.prepareStatement("insert into colleges(name,close_rank) values(?,?)");
			Prestmt.setString(1,app[0]);
			Prestmt.setInt(2, Integer.parseInt(app[1]));
			Prestmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public static void display2(String[] app, Connection myConn) {
		try {
                    PreparedStatement Prestmt;
                    //SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                    //java.util.Date date = sdf1.parse(app[1]);
                    //java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());  
                    Prestmt = myConn.prepareStatement("insert into login(email,password,name,choice) values(?,?,?,?)");
                    Prestmt.setString(1,app[0]);
                    Prestmt.setString(2,app[1]);
                    Prestmt.setString(3, app[2]);
                    Prestmt.setInt(4, Integer.parseInt(app[3]));
                    Prestmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) throws IOException{
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Bala\\Documents\\NetBeansProjects\\SPD_Mini_Project\\src\\Login_Details.csv"));
			int flag = 0;
			String line = " ";
			String split = ",";
			while((line = br.readLine())!= null) {
				if(flag == 0) {
					flag = 1;
					continue;
				}
				String[] app = line.split(split);
				for(int i=0;i<4;i++) {
				    System.out.print(app[i] + " ");
				}
				System.out.println();
				display2(app,myConn);
			}
			br.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
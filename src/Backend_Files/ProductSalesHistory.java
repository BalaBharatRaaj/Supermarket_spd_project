/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend_Files;
import java.sql.*;
/**
 *
 * @author Bala
 */
public class ProductSalesHistory {
    private int[] array1;
    private String[] array2;

    public ProductSalesHistory(int[] ordered_count, String[] ordered_dates){
        this.array1 = ordered_count;
        this.array2 = ordered_dates;
    }
    
    public int[] product_sales_month(String product_name, int product_id, int month){
        int[] ordered = new int[13];
        for(int i = 0; i<12; i++){
            ordered[i] = 0;
        }
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini-project", "root", "root");
            PreparedStatement Prestmt;
            if(product_id != -1){
                Prestmt = myConn.prepareStatement("select month(date_of_purchase) as month_purchase from product_sales where product_id = ?");
                Prestmt.setInt(1, product_id);
                ResultSet myRs = Prestmt.executeQuery();
                while(myRs.next()){
                    ordered[myRs.getInt("month_purchase")] += 1;
                }
                return ordered;
            }
            else if(product_name.equals("") != true){
                Prestmt = myConn.prepareStatement("select month(date_of_purchase) as month_purchase from product_sales where product_id = ?");
                Prestmt.setString(1, product_name);
                ResultSet myRs = Prestmt.executeQuery();
                while(myRs.next()){
                    ordered[myRs.getInt("month_purchase")] += 1;
                }
                return ordered;
            }
            else{
                System.out.println("Both the values are empty");
                return new int[] {-1};
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
            return new int[] {-1};
        }
    }
    
    public int[] product_sales_year(String product_name, int product_id, int year){
        int[] ordered = new int[13];
        for(int i = 0; i<12; i++){
            ordered[i] = 0;
        }
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini-project", "root", "root");
            PreparedStatement Prestmt;
            if(product_id != -1){
                Prestmt = myConn.prepareStatement("select month(date_of_purchase) as month_purchase from product_sales where product_id = ? and year = ?");
                Prestmt.setInt(1, product_id);
                ResultSet myRs = Prestmt.executeQuery();
                while(myRs.next()){
                    ordered[myRs.getInt("month_purchase")] += 1;
                }
                return ordered;
            }
            else if(product_name.equals("") != true){
                Prestmt = myConn.prepareStatement("select month(date_of_purchase) as month_purchase from product_sales where product_id = ? and year = ?");
                Prestmt.setString(1, product_name);
                ResultSet myRs = Prestmt.executeQuery();
                while(myRs.next()){
                    ordered[myRs.getInt("month_purchase")] += 1;
                }
                return ordered;
            }
            else{
                System.out.println("Both the values are empty");
                return new int[] {-1};
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
            return new int[] {-1};
        }
    }
    
    public ProductSalesHistory product_sales_dates(String product_name, int product_id, String start_date, String end_date){
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini-project", "root", "root");
            PreparedStatement Prestmt;
            if(product_id != -1){
                Prestmt = myConn.prepareStatement("select count(distinct date_of_purchase) as count_date from product_sales where product_id = ? and (date_of purchase between ? and ?)");
                Prestmt.setInt(1, product_id);
                Prestmt.setDate(2, Date.valueOf(start_date));
                Prestmt.setDate(2, Date.valueOf(end_date));
                ResultSet myRs = Prestmt.executeQuery();
                myRs.next();
                
                String[] ordered_date = new String[myRs.getInt("count_date")];
                int[] ordered_count = new int[myRs.getInt("count_date")];
                
                Prestmt = myConn.prepareStatement("select count(date_of_purchase) as count_date, date_of_purchase from product_sales where product_id = ? and (date_of purchase between ? and ?) group by date_of_purchase order by date_of_purchase");
                Prestmt.setInt(1, product_id);
                Prestmt.setDate(2, Date.valueOf(start_date)); //yyyy-mm-dd format
                Prestmt.setDate(2, Date.valueOf(end_date));
                myRs = Prestmt.executeQuery();
                
                int index = 0;
                
                while(myRs.next()){
                    ordered_date[index] = myRs.getDate("date_of_purchase").toString();
                    ordered_count[index] = myRs.getInt("count_date");
                    index++;
                }
                return new ProductSalesHistory(ordered_count, ordered_date);
            }
            else if(product_name.equals("") != true){
                Prestmt = myConn.prepareStatement("select count(distinct date_of_purchase) as count_date from product_sales where product_name = ? and (date_of purchase between ? and ?)");
                Prestmt.setInt(1, product_id);
                Prestmt.setDate(2, Date.valueOf(start_date));
                Prestmt.setDate(2, Date.valueOf(end_date));
                ResultSet myRs = Prestmt.executeQuery();
                myRs.next();
                
                String[] ordered_date = new String[myRs.getInt("count_date")];
                int[] ordered_count = new int[myRs.getInt("count_date")];
                
                Prestmt = myConn.prepareStatement("select count(date_of_purchase) as count_date, date_of_purchase from product_sales where product_name = ? and (date_of purchase between ? and ?) group by date_of_purchase order by date_of_purchase");
                Prestmt.setInt(1, product_id);
                Prestmt.setDate(2, Date.valueOf(start_date));
                Prestmt.setDate(2, Date.valueOf(end_date));
                myRs = Prestmt.executeQuery();
                
                int index = 0;
                
                while(myRs.next()){
                    ordered_date[index] = myRs.getDate("date_of_purchase").toString();
                    ordered_count[index] = myRs.getInt("count_date");
                    index++;
                }
                return new ProductSalesHistory(ordered_count, ordered_date);
            }
            else{
                System.out.println("Both the values are empty");
                return new ProductSalesHistory(new int[] {-1}, new String[] {"Error"});
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
            return new ProductSalesHistory(new int[] {-1}, new String[] {"Error"});
        }
    }
}

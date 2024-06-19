package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import com.mycompany.database.databaseConnection;
import com.mycompany.entity.accountant;
import com.mycompany.entity.customer;
import com.mycompany.exception.accountantException;
import com.mycompany.exception.customerException;

public class accountantDaoImplementation  implements accountantDao{


    public accountant loginAccountant(String accountantUsername, String accountantPassword) throws accountantException {
    accountant acc=null;
    try(Connection connection=databaseConnection.provideConnection()) {
        PreparedStatement ps=connection.prepareStatement("select * from accountant where accountantUsername=? and accountantPassword=?");
        ps.setString(1, accountantUsername);
        ps.setString(2, accountantPassword);

        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            String n=rs.getString("accountantUsername");
            String e=rs.getString("accountantEmail");
            String p=rs.getString("accountantPassword");

            acc=new accountant(n, e, p);
        }
    } catch (SQLException e) {
        // TODO: handle exception
        throw new accountantException("invaild username or password");
    }
      return acc;
    }
    public int addCustomer(String customerName, String customerEmail, String customerPassword, String customerAddress, String customerMobile) throws customerException {
        int cid=-1;
        try(Connection connection=databaseConnection.provideConnection()) {
            PreparedStatement ps=connection.prepareStatement("insert into customerinformation(customerName, customerEmail, customerPassword,  customerMobile,customerAddress ) values(?,?,?,?,?)");
            ps.setString(1, customerName);
            ps.setString(2, customerEmail);
            ps.setString(3, customerPassword);
            ps.setString(4 , customerMobile);
            ps.setString(5, customerAddress);
            //update the database with the new customer

            int x=ps.executeUpdate();
            if(x>0){
                System.out.println("Customer added successfully"); 
                PreparedStatement ps1=connection.prepareStatement("select cid from customerinformation where customerEmail=? and customerMobile=?");
                ps1.setString(1, customerEmail);
                ps1.setString(2, customerMobile);
                ResultSet rs=ps1.executeQuery();

                if(rs.next()){
                    cid=rs.getInt("cid");
                }
                else{
                    throw new customerException("inserted data is not correct");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new customerException("Error in adding customer");
         }
        return cid;
        }
    public String addAccount(int customerBalance, int cid) throws customerException {
        String message=null;
        try(Connection connection=databaseConnection.provideConnection()){
            PreparedStatement ps=connection.prepareStatement("insert into account(customerBalance, cid) values(?,?)");
            ps.setInt(1, customerBalance);
            ps.setInt(2, cid);

            int x=ps.executeUpdate();
            if(x>0){
                message="Account added successfully";
            }
            else
            {
                message="Account not added, inserted data is not correct";
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new customerException("SQL Error in adding account");
        }
        return message;
    }
    public String updateCustomer(int customerAccountNumber, String customerAddress) throws customerException {
        String message=null;
    try (Connection connection=databaseConnection.provideConnection()){
        PreparedStatement ps=connection.prepareStatement("update customerinformation i inner join account a on i.cid=a.cid and a.customerAccountNumber=? set i.customerAddress=? ");
        ps.setInt(1, customerAccountNumber);
        ps.setString(2, customerAddress);

        int x=ps.executeUpdate();
        if(x>0){
            message="Customer address updated successfully";
        }
        else{
            message="Customer address not updated, inserted data is not correct";
        }
    } catch (SQLException e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    return message;
    }
    public String deleteCustomer(int customerAccountNumber) throws customerException{
        String message=null;
        try(Connection connection=databaseConnection.provideConnection()){
            PreparedStatement ps=connection.prepareStatement("delete i from customerinformation i inner join account a on i.cid=a.cid where a.customerAccountNumber=?");
            ps.setInt(1, customerAccountNumber);

            int x=ps.executeUpdate();
            if(x>0){
                message="Customer deleted successfully";
            }
            else{
                message="Customer not deleted, inserted data is not correct";
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();      
        }
        return message;
    }
    public customer viewCustomer(int customerAccountNumber) throws customerException {

        customer cu=null;
        try(Connection connection=databaseConnection.provideConnection()){
            PreparedStatement ps=connection.prepareStatement("select * from customerinformation i inner join account a on i.cid=a.cid where customerAccountNumber=?");
            ps.setInt(1, customerAccountNumber);

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                int accountNumber=rs.getInt("customerAccountNumber");
                String name=rs.getString("customerName");
                int balance=rs.getInt("customerBalance");
                String address=rs.getString("customerAddress");
                String email=rs.getString("customerEmail");
                String password=rs.getString("customerPassword");
                String mobile=rs.getString("customerMobile");

                cu=new customer(accountNumber, name, balance, address, email, password, mobile);

            }
            else{
                throw new customerException("Customer not found");
            }
            
        } catch (SQLException e) {
            // TODO: handle exception
        }
        return cu;
    }
    public customer viewAllCustomer() throws customerException {
        customer cu=null;
        try(Connection connection=databaseConnection.provideConnection()) {
            PreparedStatement ps=connection.prepareStatement("select * from customerinformation i inner join account a on a.cid=i.cid");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int accountNumber=rs.getInt("customerAccountNumber");
                String name=rs.getString("customerName");
                int balance=rs.getInt("customerBalance");
                String address=rs.getString("customerAddress");
                String email=rs.getString("customerEmail");
                String password=rs.getString("customerPassword");
                String mobile=rs.getString("customerMobile");

                System.out.println("Customer Account Number: "+accountNumber);
                System.out.println("Customer Name: "+name);
                System.out.println("Customer Balance: "+balance);
                System.out.println("Customer Address: "+address);
                System.out.println("Customer Email: "+email);
                System.out.println("Customer Password: "+password);
                System.out.println("Customer Mobile: "+mobile);
                System.out.println("********************************************");

                cu=new customer(accountNumber, name, balance, address, email, password, mobile);

            }
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return cu;

    }
}

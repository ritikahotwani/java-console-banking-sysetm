package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.database.databaseConnection;
import com.mycompany.entity.customer;
import com.mycompany.exception.customerException;

public class customerDaoImplementation implements customerDao {

    @Override
    public customer loginCustomer(String customerName, String customerPassword, int customerAccountNumber)throws customerException {
        customer cu= null;
        try(Connection connection=databaseConnection.provideConnection()) {
            PreparedStatement ps=connection.prepareStatement("select * from customerinformation i inner join account a on i.cid=a.cid where customerName=? and customerPassword=? and customerAccountNumber=?");
            ps.setString(1, customerName);
            ps.setString(2, customerPassword);
            ps.setInt(3, customerAccountNumber);

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
               int accountNumber=rs.getInt("customerAccountNumber");
               String name=rs.getString("customerName");
               int balance=rs.getInt("customerBalance");
                String email=rs.getString("customerEmail");
                String password=rs.getString("customerPassword");
                String mobile=rs.getString("customerMobile");
                String address=rs.getString("customerAddress");
                cu=new customer(accountNumber, name, balance, address, email, password, mobile);

            }
            else{

                throw new customerException("invaild name and password");
            }
            
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return cu;
    }

    @Override
    public int viewBalance(int customerAccountNumber) throws customerException {
        int balance=-1;
        try(Connection connection=databaseConnection.provideConnection()) {
            PreparedStatement ps=connection.prepareStatement("select customerBalance from account where customerAccountNumber=?");
            ps.setInt(1, customerAccountNumber);

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                balance=rs.getInt("customerBalance");
            }
            else{
                throw new customerException("invaild account number");
            }
            
        } catch (SQLException e) {
            // TODO: handle exception
            throw new customerException(e.getMessage());

        }
        return balance;

    }

    @Override
    public int Deposit(int customerAccountNumber, int amount) throws customerException {
        int balance=-1;
        try(Connection connection=databaseConnection.provideConnection()) {
            PreparedStatement ps=connection.prepareStatement("update account set customerBalance= customerBalance+? where customerAccountNumber=?");
            ps.setInt(1, amount);
            ps.setInt(2, customerAccountNumber);

            int result=ps.executeUpdate();
            if(result==1){
                balance=viewBalance(customerAccountNumber);
            }
            else{
                throw new customerException("invaild account number");
            }
        } catch (SQLException e) {
            // TODO: handle exception
            throw new customerException(e.getMessage());
        }
        return balance;
    }

    @Override
    public int Widhdraw(int customerAccountNumber, int amount) throws customerException {
        int balance=viewBalance(customerAccountNumber);
        if(balance<amount){
            throw new customerException("insufficient balance");
        }
        else{
            try(Connection connection=databaseConnection.provideConnection()) {
                PreparedStatement ps=connection.prepareStatement("update account set customerBalance=customerBalance-? where customerAccountNumber=?");
                ps.setInt(1, amount);
                ps.setInt(2, customerAccountNumber);
                int rs=ps.executeUpdate();
                if(rs==1){
                    balance=viewBalance(customerAccountNumber);
                }
                else{
                    throw new customerException("invaild account number");
                }
            } catch (SQLException e) {
                // TODO: handle exception
                throw new customerException(e.getMessage());
            }
            return balance;
        }
    }

    @Override
    public int Transfer(int customerAccountNumber, int receiverAccountNumber, int amount) throws customerException {
        int balance=viewBalance(customerAccountNumber);
        if(balance<amount){
            throw new customerException("insufficient balance");
        }
        else if(!checkAccountNumber(receiverAccountNumber)){
        throw new customerException("invaild receiver account number");
        }
        else{
            int w=Widhdraw(customerAccountNumber, amount);
            int d=Deposit(receiverAccountNumber, amount);
            if(w==-1 || d==-1){
                throw new customerException("transaction failed");
            }
            

        }
        return viewBalance(customerAccountNumber);
    
}

    private boolean checkAccountNumber(int receiverAccountNumber) {
        boolean flag=false;
        try (Connection connection=databaseConnection.provideConnection()){
            PreparedStatement ps=connection.prepareStatement("select * from account where customerAccountNumber=?");
            ps.setInt(1, receiverAccountNumber);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                flag=true;
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage()); 
        }
        return flag;
    }
}
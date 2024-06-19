package com.mycompany.dao;
import com.mycompany.entity.customer;
import com.mycompany.exception.customerException;

public interface customerDao {
    public customer loginCustomer(String customerName, String customerPassword, int customerAccountNumber) throws customerException;
    public int viewBalance(int customerAccountNumber) throws customerException;
    public int Deposit(int customerAccountNumber, int amount) throws customerException;
    public int Widhdraw(int customerAccountNumber, int amount) throws customerException;
    public int Transfer(int customerAccountNumber, int receiverAccountNumber, int amount) throws customerException;
}

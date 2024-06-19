package com.mycompany.dao;

import com.mycompany.entity.accountant;
import com.mycompany.entity.customer;
import com.mycompany.exception.accountantException;
import com.mycompany.exception.customerException;

public interface accountantDao {
    public accountant loginAccountant(String accountantUsername, String accountantPassword) throws accountantException;
    public int addCustomer(String customerName, String customerEmail, String customerPassword, String custmoerMobile, String customerAddress) throws customerException;
    public String addAccount(int customerBalance, int cid) throws customerException;
    public String updateCustomer(int customerAccountNumber,String customerAddress) throws customerException;
    public String deleteCustomer(int customerAccountNumber) throws customerException;
    public customer viewCustomer(int customerAccountNumber) throws customerException;
    public customer viewAllCustomer() throws customerException;


}


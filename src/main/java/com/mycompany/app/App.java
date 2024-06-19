package com.mycompany.app;
import java.util.Scanner;
import com.mycompany.dao.accountantDao;
import com.mycompany.dao.accountantDaoImplementation;
import com.mycompany.dao.customerDao;
import com.mycompany.dao.customerDaoImplementation;
import com.mycompany.entity.accountant;
import com.mycompany.entity.customer;
import com.mycompany.exception.accountantException;
import com.mycompany.exception.customerException;

public class App 
{
    public static void main( String[] args )
    {
        // System.out.println( "Hello World!" );

        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println("Welcome to Online Banking System"); 

            // -----------------Login Menu-----------------


            System.out.println("1. Admin  \r\n"+ "2. Customer \r\n"+ "3. Exit \r\n"+ "------------------------------------------\r\n");
            System.out.println("Enter your choice");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Welcome to Admin login-----fill in accountant login credentials");
                    System.out.println("Enter username");
                    String username = sc.next();
                    System.out.println("Enter password");
                    String password = sc.next();
                    accountantDao ad = new accountantDaoImplementation();
                    try {
                        accountant a=ad.loginAccountant(username, password);
                        if(a==null){
                            System.out.println("Invalid username or password");
                            break;
                        }
                        System.out.println("Admin login successfull");
                        System.out.println("Welcome "+a.getAccountantUsername());

                        // -----------------Admin Menu-----------------

                        boolean x=true;
                        while (x) {
                            System.out.println("--------\r\n"+
                            "1. Add Customer\r\n"
                            + "2. Update Customer Address\r\n"
                            + "3. Delete Customer\r\n"
                            + "4. View Customer\r\n"
                            + "5. View All Customer\r\n"
                            + "6. Logout\r\n");
    
                            int adminChoice = sc.nextInt();
                            if(adminChoice==1){
                                System.out.println("------------New Account----------");
                                
                                System.out.println("Enter customer name");
                                String customerName = sc.next();

                                System.out.println("Enter account opening balance");
                                int customerBalance = sc.nextInt();

                                System.out.println("Enter customer email");
                                String customerEmail = sc.next();
    
                                System.out.println("Enter customer password");
                                String customerPassword = sc.next();
    
                                System.out.println("Enter customer mobile");
                                String customerMobile = sc.next();
    
                                System.out.println("Enter customer address");
                                String customerAddress = sc.next();

                                int s=-1;
                                try {
                                    s=ad.addCustomer(customerName ,customerEmail, customerPassword, customerMobile , customerAddress);
                                    try {
                                        ad.addAccount(customerBalance, s);
                                    } catch (Exception e) {
                                        // TODO: handle exception
                                        System.out.println(e.getMessage());
                                        
                                    }
                                } catch (customerException e) {
                                    System.out.println(e.getMessage());

                                }

                            }
                            if(adminChoice==2){
                                System.out.println("------------Update Customer Address----------");
                                System.out.println("Enter customer account number");
                                int customerAccountNumber = sc.nextInt();
                                System.out.println("Enter customer address");
                                String customerAddress = sc.next();
                                try {
                                    String message =ad.updateCustomer(customerAccountNumber, customerAddress);
                                    System.out.println(message);
                                } catch (customerException e) {
                                    System.out.println(e.getMessage());
                            }
                        }
                        if(adminChoice==3){
                            System.out.println("------------Delete Customer----------");
                            System.out.println("Enter customer account number");
                            int customerAccountNumber = sc.nextInt();
                            try {
                                String message =ad.deleteCustomer(customerAccountNumber);
                                System.out.println(message);
                            } catch (customerException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        if(adminChoice==4){
                            System.out.println("------------View Customer----------");
                            System.out.println("Enter customer account number");
                            int customerAccountNumber = sc.nextInt();
                            try {
                                customer c=ad.viewCustomer(customerAccountNumber);
                                if(c==null){
                                    System.out.println("No customer found with this account number");
                                }
                                else{
                                    // System.out.println(c);
                                    System.out.println("Account Number "+c.getCustomerAccountNumber());
                                    System.out.println("Name "+c.getCustomerName());
                                    System.out.println("Email "+c.getCustomerEmail());
                                    System.out.println("Mobile "+c.getCustomerMobile());
                                    System.out.println("Balance "+c.getCustomerBalance());
                                    System.out.println("Address "+c.getCustomerAddress());
                                    System.out.println("Password "+c.getCustomerPassword());
                                    System.out.println("------------------------------------------");
                                }
                            } catch (Exception e) {
                                // TODO: handle exception
                                System.out.println(e.getMessage());
                            }
                        }
                        if(adminChoice==5){
                            System.out.println("------------View All Customer----------");
                            try {
                                System.out.println("-----------------All Customers----------------");
                                customer cu=ad.viewAllCustomer();
                                if(cu==null){
                                    System.out.println("No customer found");
                                }
                            } catch (Exception e) {
                                // TODO: handle exception
                                System.out.println(e.getMessage());
                            }
                        }
                        if(adminChoice==6){
                            x=false;    
                            System.out.println("Admin logout successfull");
                        }
                        }
                        break;

                    } 
                    catch (accountantException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Welcome to Customer login-----fill in your login credentials");
                    System.out.println("Enter username");
                    String customerName = sc.next();
                    System.out.println("Enter password");
                    String customerPassword = sc.next();
                    System.out.println("Enter account number");
                    int customerAccountNumber = sc.nextInt();
                    
                    customerDao cd=new customerDaoImplementation();
                    try {
                        customer c=cd.loginCustomer(customerName, customerPassword,  customerAccountNumber);
                        if(c==null){
                            System.out.println("Invalid username or password");
                            // break;
                        }

                            System.out.println("Customer login successfull");
                            System.out.println("Welcome "+c.getCustomerName());
                    
                        // -----------------Customer Menu-----------------
                        boolean y=true;
                        while(y){
                            System.out.println("------------------------------------\r\n"+
                           "1. View Balance\r\n"+
                           "2. Deposit\r\n"
                           +"3. Widhraw\r\n"+
                           "4. Transfer\r\n"+
                           "5. Logout\r\n");

                           int customerChoice=sc.nextInt();

                           if(customerChoice==1){
                            try {
                                int balance=cd.viewBalance(customerAccountNumber);
                                System.out.println("----------------Account balance--------------------");
                                System.out.println("Your account balance is "+balance);
                                System.out.println("------------------------------------\r\n");

                            } catch (customerException e) {
                                // TODO: handle exception
                                System.out.println(e.getMessage());

                            }
                           }
                           if(customerChoice==2){
                            System.out.println("------------Deposit----------");
                            System.out.println(("Enter amount to deposit"));
                            int amount=sc.nextInt();
                            try {
                                int balance=cd.Deposit(customerAccountNumber, amount);
                                System.out.println("Amount deposited successfully");
                                System.out.println("Your current balance is "+balance);
                            } catch (customerException e) {
                                System.out.println(e.getMessage());
                            }
                           }
                           if(customerChoice==3){
                            System.out.println("------------Widhraw----------");
                            System.out.println("Enter amount to widhraw");
                            int amount=sc.nextInt();
                            try {
                                int balance=cd.Widhdraw(customerAccountNumber, amount);
                                System.out.println("Amount widhraw successfully");  
                                System.out.println("Your current balance is "+balance);
                            } catch (customerException e) {
                                // TODO: handle exception
                                System.out.println(e.getMessage());
                            }
                           }
                           if (customerChoice==4) {
                            System.out.println("------------Transfer----------");
                            System.out.println("Enter receiver account number");
                            int receiverAccountNumber=sc.nextInt();
                            System.out.println("Enter amount to transfer");
                            int amount=sc.nextInt();
                            try {
                                int balance=cd.Transfer(customerAccountNumber, receiverAccountNumber, amount);
                                System.out.println("Amount transfered successfully");
                                System.out.println("Your current balance is "+balance);

                            } catch (customerException e) {
                                // TODO: handle exception
                                System.out.println(e.getMessage());
                            }
                            
                           }
                           if(customerChoice==5){
                            y=false;    
                            System.out.println("Customer logout successfull");
                        }
                        }
                        break;
                    }catch (customerException e) {
                        System.out.println("Error: " + e.getMessage());
                    } 
                    break;
            
                case 3:
                    flag=false;
                    System.out.println("Thank you for using our service!!!!");
                    break;
                }


        }
    }
}

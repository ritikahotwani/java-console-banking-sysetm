package com.mycompany.entity;

public class customer {
    private int customerAccountNumber;
    private String customerName;
    private int customerBalance;
    private String customerAddress;
    private String customerEmail;
    private String customerPassword;
    private String customerMobile;
    @Override
    public String toString() {
        return "customer [customerAccountNumber=" + customerAccountNumber + ", customerName=" + customerName
                + ", customerBalance=" + customerBalance + ", customerAddress=" + customerAddress + ", customerEmail="
                + customerEmail + ", customerPassword=" + customerPassword + ", customerMobile=" + customerMobile + "]";
    }
    public int getCustomerAccountNumber() {
        return customerAccountNumber;
    }
    public void setCustomerAccountNumber(int customerAccountNumber) {
        this.customerAccountNumber = customerAccountNumber;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public int getCustomerBalance() {
        return customerBalance;
    }
    public void setCustomerBalance(int customerBalance) {
        this.customerBalance = customerBalance;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public String getCustomerPassword() {
        return customerPassword;
    }
    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
    public String getCustomerMobile() {
        return customerMobile;
    }
    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }
    public customer() {
    }
    public customer(int customerAccountNumber, String customerName, int customerBalance, String customerAddress,
            String customerEmail, String customerPassword, String customerMobile) {
        this.customerAccountNumber = customerAccountNumber;
        this.customerName = customerName;
        this.customerBalance = customerBalance;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.customerMobile = customerMobile;
    }
}

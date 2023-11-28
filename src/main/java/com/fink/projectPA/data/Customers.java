/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.data;

/**
 *
 * @author Ivana
 */
public class Customers {
    private int customerId=-1;
    private String customerName;
    private String contactPerson;
    private String adress;
    private String city;
    private String postCode;
    private String country;

    public Customers(String customerName, String contactPerson, String adress, String city, String postCode, String country) {
        this.customerName = customerName;
        this.contactPerson = contactPerson;
        this.adress = adress;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }
     public Customers(int customerId, String customerName, String contactPerson, String adress, String city, String postCode, String country) {
        this.customerId=customerId;
        this.customerName = customerName;
        this.contactPerson = contactPerson;
        this.adress = adress;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getAdress() {
        return adress;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCountry() {
        return country;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Customers{" + "customerId=" + customerId + ", customerName=" + customerName + ", contactPerson=" + contactPerson + ", adress=" + adress + ", city=" + city + ", postCode=" + postCode + ", country=" + country + '}';
    }
    
    
    
    
    
    
    
    
}

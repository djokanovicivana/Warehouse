/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.data;

/**
 *
 * @author Ivana
 */
public class Suppliers {
    private int supplierId=-1;
    private String supplierName;
    private String contactPerson;
    private String adress;
    private String city;
    private String postCode;
    private String country;
    private String phone;

    public Suppliers(String supplierName, String contactPerson, String adress, String city, String postCode, String country, String phone) {
        this.supplierName = supplierName;
        this.contactPerson = contactPerson;
        this.adress = adress;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
        this.phone = phone;
    }
    
       public Suppliers(int supplierId, String supplierName, String contactPerson, String adress, String city, String postCode, String country, String phone) {
        this.supplierId=supplierId;
        this.supplierName = supplierName;
        this.contactPerson = contactPerson;
        this.adress = adress;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
        this.phone = phone;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
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

    public String getPhone() {
        return phone;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Suppliers{" + "supplierId=" + supplierId + ", supplierName=" + supplierName + ", contactPerson=" + contactPerson + ", adress=" + adress + ", city=" + city + ", postCode=" + postCode + ", country=" + country + ", phone=" + phone + '}';
    }
       
    
    
    
}

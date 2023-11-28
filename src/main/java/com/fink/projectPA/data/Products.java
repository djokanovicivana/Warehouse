/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.data;

/**
 *
 * @author Ivana
 */
public class Products {
    private int productId=-1;
    private String productName;
    private Suppliers supplier;
    private String productCategory;
    private double pricePerUnit;

    public Products(int productId, String productName, Suppliers supplier, String productCategory, double pricePerUnit) {
        this.productId = productId;
        this.productName = productName;
        this.supplier = supplier;
        this.productCategory = productCategory;
        this.pricePerUnit = pricePerUnit;
    }

    public Products(String productName, Suppliers supplier, String productCategory, double pricePerUnit) {
        this.productName = productName;
        this.supplier = supplier;
        this.productCategory = productCategory;
        this.pricePerUnit = pricePerUnit;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString() {
        return "Products{" + "productId=" + productId + ", productName=" + productName + ", supplier=" + supplier + ", productCategory=" + productCategory + ", pricePerUnit=" + pricePerUnit + '}';
    }
    
    
    
    
    
}

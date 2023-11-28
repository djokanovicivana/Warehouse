/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.data;

/**
 *
 * @author Ivana
 */
public class OrderDetails {
    private int orderDetailsId=-1;
    private Orders order;
    private Products product;
    private int quantity;

    public OrderDetails(Orders order, Products product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
    public OrderDetails(int orderDetailsId, Orders order, Products product, int quantity) {
        this.orderDetailsId=orderDetailsId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public Orders getOrder() {
        return order;
    }

    public Products getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "orderDetailsId=" + orderDetailsId + ", order=" + order + ", product=" + product + ", quantity=" + quantity + '}';
    }  
}

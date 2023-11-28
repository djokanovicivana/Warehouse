/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.data;

import java.sql.Date;

/**
 *
 * @author Ivana
 */
public class Orders {
    private int orderId=-1;
    private Date orderDate;
    private Customers customer;
    private Employees employee;
    private Shippers shipper;

    public Orders(Date orderDate, Customers customer, Employees employee, Shippers shipper) {
        this.orderDate = orderDate;
        this.customer = customer;
        this.employee = employee;
        this.shipper = shipper;
    }
      public Orders(int orderId, Date orderDate, Customers customer, Employees employee, Shippers shipper) {
        this.orderId=orderId;
        this.orderDate = orderDate;
        this.customer = customer;
        this.employee = employee;
        this.shipper = shipper;
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Customers getCustomer() {
        return customer;
    }

    public Employees getEmployee() {
        return employee;
    }

    public Shippers getShipper() {
        return shipper;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public void setShipper(Shippers shipper) {
        this.shipper = shipper;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderId=" + orderId + ", orderDate=" + orderDate + ", customer=" + customer + ", employee=" + employee + ", shipper=" + shipper + '}';
    }
    
    
    
    
    
    
}

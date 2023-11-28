/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.data;

/**
 *
 * @author Ivana
 */
public class Shippers {
    private int shipperId=-1;
    private String shipperName;
    private String phone;

    public Shippers(String shipperName, String phone) {
        this.shipperName = shipperName;
        this.phone = phone;
    }
    
    public Shippers(int shipperId, String shipperName, String phone) {
        this.shipperId=shipperId;
        this.shipperName = shipperName;
        this.phone = phone;
    }

    public int getShipperId() {
        return shipperId;
    }

    public String getShipperName() {
        return shipperName;
    }

    public String getPhone() {
        return phone;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public void setPhone(String phone) {
        this.phone= phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shippers{");
        sb.append("shipperId=").append(shipperId);
        sb.append(", shipperName=").append(shipperName);
        sb.append(", phoneNumber=").append(phone);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
}

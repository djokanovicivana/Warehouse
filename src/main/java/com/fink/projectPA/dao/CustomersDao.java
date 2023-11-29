/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.dao;

import com.fink.projectPA.data.Customers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class CustomersDao {
     private static final CustomersDao instance = new CustomersDao();
    
    private CustomersDao() {
    }

    public static CustomersDao getInstance() {
        return instance;
    }
    
    public void create(Connection con, Customers customer)throws SQLException{
        PreparedStatement ps=null;
        String sql="INSERT into Customers (CustomerName, ContactPerson, Adress, City, PostCode, Country) VALUES (?,?,?,?,?,?)";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getContactPerson());
            ps.setString(3, customer.getAdress());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getPostCode());
            ps.setString(6, customer.getCountry());
            ps.executeUpdate();
        }
        finally{
          ResourceManager.closeResources(null,ps);
        }
    }
    public void update(Connection con, Customers customer)throws SQLException{
        PreparedStatement ps=null;
        String sql="UPDATE Customers SET CustomerName=?,ContactPerson=?,Adress=?,City=?,PostCode=?,Country=? WHERE CustomerId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getContactPerson());
            ps.setString(3, customer.getAdress());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getPostCode());
            ps.setString(6, customer.getCountry());
            ps.setInt(7, customer.getCustomerId());
            ps.executeUpdate();
            
            
        }
        finally{
            ResourceManager.closeResources(null, ps);
        }
    }
    public void delete(Connection con, int customerId) throws SQLException{
        PreparedStatement ps=null;
        String sql="DELETE FROM Customers WHERE CustomerId=?";
        try{
           ps=con.prepareStatement(sql);
           ps.setInt(1, customerId);
           ps.executeUpdate();
        }
        finally{
            ResourceManager.closeResources(null, ps);
        }
    }
    public Customers find(Connection con, int customerId)throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        Customers customer=null;
        String sql="SELECT * FROM Customers WHERE CustomerId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, customerId);
            rs=ps.executeQuery();
            if(rs.next()){
                customer=new Customers(rs.getInt("CustomerId"), rs.getString("CustomerName"), rs.getString("ContactPerson"), rs.getString("Adress"), rs.getString("City"), rs.getString("PostCode"),rs.getString("Country"));
            }else{
                return null;
            }
        }
        finally{
            ResourceManager.closeResources(rs,ps);
        }
        return customer;
    }
    public ArrayList<Customers> findAll(Connection con) throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList <Customers> customers=new ArrayList<Customers>();
        String sql="SELECT * FROM Customers";
        try{
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                customers.add(new Customers(rs.getInt("CustomerId"), rs.getString("CustomerName"), rs.getString("ContactPerson"), rs.getString("Adress"), rs.getString("City"), rs.getString("PostCode"),rs.getString("Country")));
            }}
            finally{
                    ResourceManager.closeResources(rs,ps);
                    }
        return customers;
        
    }
    public ArrayList<Customers> findAllSortedByName(Connection con) throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList <Customers> customers=new ArrayList<Customers>();
        String sql="SELECT * FROM Customers ORDER BY CustomerName";
        try{
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                customers.add(new Customers(rs.getInt("CustomerId"), rs.getString("CustomerName"), rs.getString("ContactPerson"), rs.getString("Adress"), rs.getString("City"), rs.getString("PostCode"),rs.getString("Country")));
            }}
            finally{
                    ResourceManager.closeResources(rs,ps);
                    }
        return customers;
        
    }
}

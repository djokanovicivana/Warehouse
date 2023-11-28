/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.dao;

import com.fink.projectPA.data.Suppliers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class SuppliersDao {
      private static final SuppliersDao instance = new SuppliersDao();
    
    private SuppliersDao() {
    }

    public static SuppliersDao getInstance() {
        return instance;
    }
    public void create(Connection con, Suppliers supplier) throws SQLException{
        PreparedStatement ps=null;
        String sql="INSERT INTO Suppliers (SupplierName, ContactPerson, Adress, City, PostCode, Country) VALUES ";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContactPerson());
            ps.setString(3, supplier.getAdress());
            ps.setString(4, supplier.getCity());
            ps.setString(5, supplier.getPostCode());
            ps.setString(6, supplier.getCountry());
            ps.executeUpdate();
        }
        finally{
          ResourceManager.closeResources(null,ps);
        }
    }
     public void delete(Connection con, int supplierId) throws SQLException{
        PreparedStatement ps=null;
        String sql="DELETE FROM Suppliers WHERE SupplierId=?";
        try{
           ps=con.prepareStatement(sql);
           ps.setInt(1, supplierId);
           ps.executeUpdate();
        }
        finally{
            ResourceManager.closeResources(null, ps);
        }
    }
    public Suppliers find(Connection con, int supplierId)throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        Suppliers supplier=null;
        String sql="SELECT * FROM Suppliers WHERE SupplierId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, supplierId);
            rs=ps.executeQuery();
            if(rs.next()){
                supplier=new Suppliers(rs.getInt("SupplierId"),rs.getString("SupplierName"), rs.getString("ContactPerson"), rs.getString("Adress"), rs.getString("City"), rs.getString("PostCode"),rs.getString("Country"), rs.getString("Phone"));
            }else{
                return null;
            }
        }
        finally{
            ResourceManager.closeResources(rs,ps);
        }
        return supplier;
    }
    public ArrayList<Suppliers> findAll(Connection con) throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList <Suppliers> suppliers=new ArrayList<Suppliers>();
        String sql="SELECT * FROM Suppliers";
        try{
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                suppliers.add(new Suppliers(rs.getInt("SupplierId"),rs.getString("SupplierName"), rs.getString("ContactPerson"), rs.getString("Adress"), rs.getString("City"), rs.getString("PostCode"),rs.getString("Country"), rs.getString("Phone")));
            }}
            finally{
                    ResourceManager.closeResources(rs,ps);
                    }
        return suppliers;
        
    }
     public void update(Connection con, Suppliers supplier) throws SQLException{
        PreparedStatement ps=null;
        String sql="UPDATE Suppliers SET SupplierName=?, ContactPerson=?, Adress=?, City=?, PostCode=?, Country=?, Phone=? WHERE SupplierId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContactPerson());
            ps.setString(3, supplier.getAdress());
            ps.setString(4, supplier.getCity());
            ps.setString(4, supplier.getPostCode());
            ps.setString(5, supplier.getCountry());
            ps.setString(6, supplier.getPhone());
            ps.setInt(7, supplier.getSupplierId());
            ps.executeUpdate();
        }
        finally{
            ResourceManager.closeResources(null, ps);
        } 
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.dao;

import com.fink.projectPA.data.Shippers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class ShippersDao {
     private static final ShippersDao instance = new ShippersDao();
    
    private ShippersDao() {
    }

    public static ShippersDao getInstance() {
        return instance;
    }
   public void create(Connection con, Shippers shipper) throws SQLException{
       PreparedStatement ps=null;
       String sql="INSERT INTO Shippers (ShipperName, Phone) VALUES (?,?) ";
       try{
           ps=con.prepareStatement(sql);
           ps.setString(1, shipper.getShipperName());
           ps.setString(2, shipper.getPhone());
           ps.executeUpdate();
       }
       finally{
           ResourceManager.closeResources(null,ps);
       }
   }
    public void delete(Connection con, int shipperId) throws SQLException{
        PreparedStatement ps=null;
        String sql="DELETE FROM Shippers WHERE ShipperId=?";
        try{
           ps=con.prepareStatement(sql);
           ps.setInt(1, shipperId);
           ps.executeUpdate();
        }
        finally{
            ResourceManager.closeResources(null, ps);
        }
    }
    public Shippers find(Connection con, int shipperId) throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        Shippers shipper;
        String sql="SELECT * FROM Shippers WHERE ShipperId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1,shipperId);
            rs=ps.executeQuery();
            if(rs.next()){
                shipper=new Shippers(rs.getInt("ShipperId"), rs.getString("ShipperName"), rs.getString("Phone"));
            }else{
                return null;
            }
        }
        finally{
        ResourceManager.closeResources(rs, ps);
    }
        return shipper;
        
    }
     public ArrayList<Shippers> findAll(Connection con) throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList <Shippers> shippers=new ArrayList<Shippers>();
        String sql="SELECT * FROM Shippers";
        try{
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                shippers.add(new Shippers(rs.getInt("ShipperId"), rs.getString("ShipperName"), rs.getString("Phone")));
            }}
            finally{
                    ResourceManager.closeResources(rs,ps);
                    }
        return shippers;
        
    }
     public void update(Connection con, Shippers shipper) throws SQLException{
         PreparedStatement ps=null;
         String sql="UPDATE Shippers SET ShipperName=?, Phone=? WHERE ShipperId=?";
         try{
             ps=con.prepareStatement(sql);
             ps.setString(1, shipper.getShipperName());
             ps.setString(2, shipper.getPhone());
             ps.setInt(3, shipper.getShipperId());
             ps.executeUpdate();
         }
         finally{
             ResourceManager.closeResources(null, ps);
         }
     }
    
}

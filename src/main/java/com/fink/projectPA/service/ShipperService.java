/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.service;

import com.fink.projectPA.dao.ResourceManager;
import com.fink.projectPA.dao.ShippersDao;
import com.fink.projectPA.data.Shippers;
import com.fink.projectPA.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class ShipperService {
     
    private static final ShipperService instance = new ShipperService();

    private ShipperService() {
    }

    public static ShipperService getInstance() {
        return instance; 
    }
    public void addNewShipper(Shippers shipper) throws WarehouseException{
        Connection con=null;
        try{
           con=ResourceManager.getConnection();
           con.setAutoCommit(false);
           ShippersDao.getInstance().create(con, shipper);
           con.commit();
           
        }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to create shipper " + shipper, ex);
        
    }finally{
            ResourceManager.closeConnection(con);
        }
}
     public Shippers findShipper(int shipperId) throws WarehouseException{
         Connection con=null;
         try{
             con=ResourceManager.getConnection();
             return ShippersDao.getInstance().find(con, shipperId);
     }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find shipper with id " + shipperId, ex);}
     finally{
             ResourceManager.closeConnection(con);} 
}
     public ArrayList<Shippers> findAllShippers() throws WarehouseException{
         Connection con=null;
         try{
             con=ResourceManager.getConnection();
             return ShippersDao.getInstance().findAll(con);
         }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all shippers", ex);}
         finally{
             ResourceManager.closeConnection(con);
         }
     }
     public void deleteShipper(int shipperId) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourceManager.getConnection();
            con.setAutoCommit(false);

            Shippers shipper = ShippersDao.getInstance().find(con,shipperId);
            if (shipper != null) {
                ShippersDao.getInstance().delete(con, shipperId);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete shipper with id " + shipperId, ex);
        } finally {
            ResourceManager.closeConnection(con);
        }
    }
      public void updateShipper(Shippers shipper) throws WarehouseException{
          Connection con=null;
          try{
              con=ResourceManager.getConnection();
              con.setAutoCommit(false);
              if(shipper!=null){
              ShippersDao.getInstance().update(con, shipper);}
              con.commit();
          }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update shipper with id " + shipper.getShipperId(), ex);
      }finally{
              ResourceManager.closeConnection(con);
          }}
    
}

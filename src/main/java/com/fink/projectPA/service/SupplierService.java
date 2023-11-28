/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.service;

import com.fink.projectPA.dao.ResourceManager;
import com.fink.projectPA.dao.SuppliersDao;
import com.fink.projectPA.data.Suppliers;
import com.fink.projectPA.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class SupplierService {
    
     public void addNewSupplier(Suppliers supplier) throws WarehouseException{
        Connection con=null;
        try{
           con=ResourceManager.getConnection();
           con.setAutoCommit(false);
           SuppliersDao.getInstance().create(con, supplier);
           con.commit();
           
        }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to create supplier " + supplier, ex);
        
    }finally{
            ResourceManager.closeConnection(con);
        }
}
      public Suppliers findSupplier(int supplierId) throws WarehouseException{
         Connection con=null;
         try{
             con=ResourceManager.getConnection();
             return SuppliersDao.getInstance().find(con, supplierId);
     }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find supplier with id " + supplierId, ex);}
     finally{
             ResourceManager.closeConnection(con);} 
}
     public ArrayList<Suppliers> findAllSuppliers() throws WarehouseException{
         Connection con=null;
         try{
             con=ResourceManager.getConnection();
             return SuppliersDao.getInstance().findAll(con);
         }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all suppliers", ex);}
         finally{
             ResourceManager.closeConnection(con);
         }
     }
     public void deleteSupplier(int supplierId) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourceManager.getConnection();
            con.setAutoCommit(false);

            Suppliers supplier = SuppliersDao.getInstance().find(con,supplierId);
            if (supplier != null) {
                SuppliersDao.getInstance().delete(con, supplierId);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete supplier with id " + supplierId, ex);
        } finally {
            ResourceManager.closeConnection(con);
        }
    }
      public void updateSupplier(Suppliers supplier) throws WarehouseException{
          Connection con=null;
          try{
              con=ResourceManager.getConnection();
              con.setAutoCommit(false);
              if(supplier!=null){
              SuppliersDao.getInstance().update(con, supplier);}
              con.commit();
          }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update supplier " + supplier, ex);
      }finally{
              ResourceManager.closeConnection(con);
          }}
    
}

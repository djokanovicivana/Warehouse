/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.service;

import com.fink.projectPA.dao.ProductsDao;
import com.fink.projectPA.dao.ResourceManager;
import com.fink.projectPA.data.Products;
import com.fink.projectPA.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class ProductService {
     
    private static final ProductService instance = new ProductService();

    private ProductService() {
    }

    public static ProductService getInstance() {
        return instance;
    }
     public void addNewProduct(Products product) throws WarehouseException{
        Connection con=null;
        try{
           con=ResourceManager.getConnection();
           con.setAutoCommit(false);
           ProductsDao.getInstance().create(con, product);
           con.commit();
           
        }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to create product " + product, ex);
        
    }finally{
            ResourceManager.closeConnection(con);
        }
}
      public Products findProduct(int productId) throws WarehouseException{
         Connection con=null;
         try{
             con=ResourceManager.getConnection();
             return ProductsDao.getInstance().find(con, productId);
     }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find product with id " + productId, ex);}
     finally{
             ResourceManager.closeConnection(con);} 
}
     public ArrayList<Products> findAllProducts() throws WarehouseException{
         Connection con=null;
         try{
             con=ResourceManager.getConnection();
             return ProductsDao.getInstance().findAll(con);
         }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all products", ex);}
         finally{
             ResourceManager.closeConnection(con);
         }
     }
     public void deleteProduct(int productId) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourceManager.getConnection();
            con.setAutoCommit(false);

            Products product = ProductsDao.getInstance().find(con,productId);
            if (product != null) {
                ProductsDao.getInstance().delete(con, productId);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete product with id " + productId, ex);
        } finally {
            ResourceManager.closeConnection(con);
        }
    }
      public void updateProduct(Products product) throws WarehouseException{
          Connection con=null;
          try{
              con=ResourceManager.getConnection();
              con.setAutoCommit(false);
              if(product!=null){
              ProductsDao.getInstance().update(con, product);}
              con.commit();
          }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update product " + product, ex);
      }finally{
              ResourceManager.closeConnection(con);
          }}
}

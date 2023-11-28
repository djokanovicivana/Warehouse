/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.service;

import com.fink.projectPA.dao.OrderDetailsDao;
import com.fink.projectPA.dao.ResourceManager;
import com.fink.projectPA.data.OrderDetails;
import com.fink.projectPA.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class OrderDetailsService {
     private static final OrderDetailsService instance = new OrderDetailsService();

    private OrderDetailsService() {
    }

    public static OrderDetailsService getInstance() {
        return instance;
    } 
    public void addNewOrderDetails(OrderDetails orderDetails) throws WarehouseException{
        Connection con=null;
        try{
           con=ResourceManager.getConnection();
           con.setAutoCommit(false);
           OrderDetailsDao.getInstance().create(con, orderDetails);
           con.commit();
           
        }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to create order details " + orderDetails, ex);
        
    }finally{
            ResourceManager.closeConnection(con);
        }
}
      public OrderDetails findOrderDetails(int orderDetailsId) throws WarehouseException{
         Connection con=null;
         try{
             con=ResourceManager.getConnection();
             return OrderDetailsDao.getInstance().find(con, orderDetailsId);
     }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find order details with id " + orderDetailsId, ex);}
     finally{
             ResourceManager.closeConnection(con);} 
}
     public ArrayList<OrderDetails> findAllOrderDetails() throws WarehouseException{
         Connection con=null;
         try{
             con=ResourceManager.getConnection();
             return OrderDetailsDao.getInstance().findAll(con);
         }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all order details", ex);}
         finally{
             ResourceManager.closeConnection(con);
         }
     }
     public void deleteOrderDetails(int orderDetailsId) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourceManager.getConnection();
            con.setAutoCommit(false);

            OrderDetails orderDetails = OrderDetailsDao.getInstance().find(con,orderDetailsId);
            if (orderDetails != null) {
                OrderDetailsDao.getInstance().delete(con, orderDetailsId);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete order details with id " + orderDetailsId, ex);
        } finally {
            ResourceManager.closeConnection(con);
        }
    }
      public void updateOrderDetails(OrderDetails orderDetails) throws WarehouseException{
          Connection con=null;
          try{
              con=ResourceManager.getConnection();
              con.setAutoCommit(false);
              if(orderDetails!=null){
              OrderDetailsDao.getInstance().update(con, orderDetails);}
              con.commit();
          }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update order details " + orderDetails, ex);
      }finally{
              ResourceManager.closeConnection(con);
          }}
    
}

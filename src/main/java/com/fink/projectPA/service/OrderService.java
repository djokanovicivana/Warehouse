/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.service;

import com.fink.projectPA.dao.OrdersDao;
import com.fink.projectPA.dao.ResourceManager;
import com.fink.projectPA.data.Orders;
import com.fink.projectPA.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class OrderService {
    
     private static final OrderService instance = new OrderService();

    private OrderService() {
    }

    public static OrderService getInstance() {
        return instance;
    }
     public void addNewOrder(Orders order) throws WarehouseException{
        Connection con=null;
        try{
           con=ResourceManager.getConnection();
           con.setAutoCommit(false);
           OrdersDao.getInstance().create(con, order);
           con.commit();
           
        }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to create order" + order, ex);
        
    }finally{
            ResourceManager.closeConnection(con);
        }
}
      public Orders findOrder(int orderId) throws WarehouseException{
         Connection con=null;
         try{
             con=ResourceManager.getConnection();
             return OrdersDao.getInstance().find(con, orderId);
     }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find order with id " + orderId, ex);}
     finally{
             ResourceManager.closeConnection(con);} 
}
     public ArrayList<Orders> findAllOrders() throws WarehouseException{
         Connection con=null;
         try{
             con=ResourceManager.getConnection();
             return OrdersDao.getInstance().findAll(con);
         }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all orders", ex);}
         finally{
             ResourceManager.closeConnection(con);
         }
     }
     public void deleteOrder(int orderId) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourceManager.getConnection();
            con.setAutoCommit(false);

            Orders order = OrdersDao.getInstance().find(con,orderId);
            if (order != null) {
                OrdersDao.getInstance().delete(con, orderId);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete order with id " + orderId, ex);
        } finally {
            ResourceManager.closeConnection(con);
        }
    }
      public void updateOrder(Orders order) throws WarehouseException{
          Connection con=null;
          try{
              con=ResourceManager.getConnection();
              con.setAutoCommit(false);
              if(order!=null){
              OrdersDao.getInstance().update(con, order);}
              con.commit();
          }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update order " + order, ex);
      }finally{
              ResourceManager.closeConnection(con);
          }}
    
}

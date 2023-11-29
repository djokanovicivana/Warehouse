/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.dao;

import com.fink.projectPA.data.OrderDetails;
import com.fink.projectPA.data.Orders;
import com.fink.projectPA.data.Products;
import com.fink.projectPA.exception.WarehouseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class OrderDetailsDao {
    
    private static final OrderDetailsDao instance=new OrderDetailsDao();

    public OrderDetailsDao() {
    }
    public static OrderDetailsDao getInstance(){
        return instance;
    }
    public void create(Connection con, OrderDetails orderDetails) throws SQLException, WarehouseException{
        PreparedStatement ps=null;
        String sql="INSERT INTO OrderDetails (OrderDetailsId, OrderId, ProductId, Quantity) VALUES(?,?,?,?)";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, orderDetails.getOrderDetailsId());
            Orders order=OrdersDao.getInstance().find(con, orderDetails.getOrder().getOrderId());
             if(order==null){
               throw new WarehouseException("Order" + orderDetails.getOrder() + "doesn't exist in database");
           }
             ps.setInt(2, order.getOrderId());
            Products product=ProductsDao.getInstance().find(con, orderDetails.getProduct().getProductId());
             if(product==null){
               throw new WarehouseException("Product" + product + "doesn't exist in database");
           }
             ps.setInt(3, product.getProductId());
             ps.setInt(4, orderDetails.getQuantity());
             ps.executeUpdate();
        }
        finally{
            ResourceManager.closeResources(null, ps);
        }
    }
    public void update(Connection con, OrderDetails orderDetails) throws SQLException{
        PreparedStatement ps=null;
        String sql="UPDATE OrderDetails SET OrderId=?, ProductId=?, Quantity=? WHERE OrderDetailsId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, orderDetails.getOrder().getOrderId());
            ps.setInt(2, orderDetails.getProduct().getProductId());
            ps.setInt(3, orderDetails.getQuantity());
            ps.setInt(4, orderDetails.getOrderDetailsId());
            ps.executeUpdate();  
        }
        finally{
            ResourceManager.closeResources(null,ps);
        }
    }
    public OrderDetails find(Connection con, int orderDetailsId) throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        OrderDetails orderDetails;
        String sql="SELECT * FROM OrderDetails WHERE OrderDetailsId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, orderDetailsId);
            rs=ps.executeQuery();
            if(rs.next()){
                Orders order=OrdersDao.getInstance().find(con, rs.getInt("OrderId"));
                Products product=ProductsDao.getInstance().find(con, rs.getInt("ProductId"));
                orderDetails=new OrderDetails(rs.getInt("OrderDetailsId"), order, product, rs.getInt("Quantity"));
            }
            else{
                return null;
            }
        }
        finally{
            ResourceManager.closeResources(rs, ps);
    }
        return orderDetails;  
}
     public OrderDetails findForOrder(Connection con, int orderId) throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        OrderDetails orderDetails;
        String sql="SELECT * FROM OrderDetails WHERE OrderId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs=ps.executeQuery();
            if(rs.next()){
                Orders order=OrdersDao.getInstance().find(con, rs.getInt("OrderId"));
                Products product=ProductsDao.getInstance().find(con, rs.getInt("ProductId"));
                orderDetails=new OrderDetails(rs.getInt("OrderDetailsId"), order, product, rs.getInt("Quantity"));
            }
            else{
                return null;
            }
        }
        finally{
            ResourceManager.closeResources(rs, ps);
    }
        return orderDetails;  
}
    public ArrayList<OrderDetails> findAll(Connection con) throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<OrderDetails> orderDetails=new ArrayList<>();
        String sql="SELECT * FROM OrderDetails";
        try{
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Orders order=OrdersDao.getInstance().find(con, rs.getInt("OrderId"));
                Products product=ProductsDao.getInstance().find(con, rs.getInt("ProductId"));
                orderDetails.add(new OrderDetails(rs.getInt("OrderDetailsId"), order, product, rs.getInt("Quantity")));
            }
        }finally{
            ResourceManager.closeResources(rs,ps);
        }
        return orderDetails;
    }
    public void delete(Connection con, Products product) throws SQLException{
        PreparedStatement ps=null;
        PreparedStatement ps1=null;
        ResultSet rs=null;
        String sql1="SELECT OrderId FROM OrderDetails WHERE ProductId=?";
        String sql="DELETE FROM OrderDetails WHERE ProductId=?";
        int orderId;
        try{
            ps1=con.prepareStatement(sql1);
            ps1.setInt(1, product.getProductId());
            rs=ps1.executeQuery();
            if(rs.next()){
                orderId=rs.getInt("OrderId");
                OrdersDao.getInstance().delete(con, orderId);
            }
            ps=con.prepareStatement(sql);
            ps.setInt(1, product.getProductId());
            ps.executeUpdate();
          
            
        }
        finally{
            ResourceManager.closeResources(null,ps);
        }
    }
     public void deleteWithOrder(Connection con, int orderId) throws SQLException{
        PreparedStatement ps=null;
        String sql="DELETE FROM OrderDetails WHERE OrderId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.executeUpdate();
        }
        finally{
            ResourceManager.closeResources(null,ps);
    }}
    
    public void delete(Connection con, int orderDetailsId) throws SQLException{
        PreparedStatement ps=null;
        String sql="DELETE FROM OrderDetails WHERE OrderDetailsId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, orderDetailsId);
            ps.executeUpdate();
        }
        finally{
            ResourceManager.closeResources(null,ps);
    }}
}
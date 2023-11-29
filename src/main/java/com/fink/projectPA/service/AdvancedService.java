/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.service;

import com.fink.projectPA.dao.CustomersDao;
import com.fink.projectPA.dao.OrderDetailsDao;
import com.fink.projectPA.dao.OrdersDao;
import com.fink.projectPA.dao.ProductsDao;
import com.fink.projectPA.dao.ResourceManager;
import com.fink.projectPA.data.Customers;
import com.fink.projectPA.data.OrderDetails;
import com.fink.projectPA.data.Orders;
import com.fink.projectPA.data.Products;
import com.fink.projectPA.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class AdvancedService {
    
     
     private static final AdvancedService instance = new AdvancedService();

    private AdvancedService() {
    }

    public static AdvancedService getInstance() {
        return instance;
    }
    
    public void listCustomersAndOrders() throws WarehouseException {
    Connection con = null;
    try {
        con = ResourceManager.getConnection();
        
        con.setAutoCommit(false);
        ArrayList<Customers> customers = CustomersDao.getInstance().findAllSortedByName(con);

        
        for (Customers customer : customers) {
            System.out.println("Customer: " + customer.getCustomerName());

            // Dohvati sve Order-e za trenutnog Customer-a
            ArrayList<Orders> orders = OrdersDao.getInstance().findForCustomer(con, customer.getCustomerId());

            // Iteriraj kroz svaki Order i ispiši OrderId
            for (Orders order : orders) {
                System.out.println("  OrderId: " + order.getOrderId());
            }
        }
    } catch (SQLException ex) {
         ResourceManager.rollbackTransactions(con);
          throw new WarehouseException("Failed to find customers and orders  " , ex);
    } finally {
        ResourceManager.closeConnection(con);
    }
}
    public ArrayList<Products> findProductsForSupplier(int supplierId) throws WarehouseException{
        Connection con=null;
        try{
            con=ResourceManager.getConnection();
            return ProductsDao.getInstance().findForSupplier(con, supplierId);
            
        }catch (SQLException ex) {
         ResourceManager.rollbackTransactions(con);
          throw new WarehouseException("Failed to find orders for supplier with id  "+ supplierId , ex);
    } finally {
        ResourceManager.closeConnection(con);
    }
        
    }
    // Metoda u AdvancedService koja vraća sve proizvode dostavljene od strane određenog isporučioca (shipper)
public ArrayList<Products> findProductsForShipper(int shipperId) throws WarehouseException {
    Connection con = null;
    try {
        con = ResourceManager.getConnection();
        con.setAutoCommit(false);

   
        ArrayList<Orders> orders = OrdersDao.getInstance().findForShipper(con, shipperId);

        ArrayList<Products> products = new ArrayList<>();

       
        for (Orders order : orders) {
            OrderDetails orderDetails = OrderDetailsDao.getInstance().findForOrder(con, order.getOrderId());
                Products product = ProductsDao.getInstance().find(con, orderDetails.getProduct().getProductId());
                products.add(product);
        }
        con.commit();
        return products;
    } catch (SQLException ex) {
        ResourceManager.rollbackTransactions(con);
        throw new WarehouseException("Failed to find products for shipper with id " + shipperId, ex);
    } finally {
        ResourceManager.closeConnection(con);
    }
}

public double totalOrderPrice() throws WarehouseException {
    Connection con = null;
    try {
        con = ResourceManager.getConnection();
        con.setAutoCommit(false);

        
        ArrayList<Orders> orders = OrdersDao.getInstance().findAll(con);

        double totalOrderPrice = 0.0;

        
        for (Orders order : orders) {
            OrderDetails orderDetails = OrderDetailsDao.getInstance().findForOrder(con, order.getOrderId());
                Products product = ProductsDao.getInstance().find(con, orderDetails.getProduct().getProductId());
                totalOrderPrice += orderDetails.getQuantity() * product.getPricePerUnit();
            
        }
        con.commit();
        return totalOrderPrice;
    } catch (SQLException ex) {
        ResourceManager.rollbackTransactions(con);
        throw new WarehouseException("Failed to calculate total order price", ex);
    } finally {
        ResourceManager.closeConnection(con);
    }
}
public double totalOrderPriceCustomer(int customerId) throws WarehouseException {
    Connection con = null;
    try {
        con = ResourceManager.getConnection();
        con.setAutoCommit(false);

        
        ArrayList<Orders> orders = OrdersDao.getInstance().findForCustomer(con, customerId);

        double totalOrderPrice = 0.0;

        
        for (Orders order : orders) {
            OrderDetails orderDetails = OrderDetailsDao.getInstance().findForOrder(con, order.getOrderId());
                Products product = ProductsDao.getInstance().find(con, orderDetails.getProduct().getProductId());
                totalOrderPrice += orderDetails.getQuantity() * product.getPricePerUnit();
            
        }
        con.commit();
        return totalOrderPrice;
    } catch (SQLException ex) {
        ResourceManager.rollbackTransactions(con);
        throw new WarehouseException("Failed to calculate total order price for customer with id +" + customerId, ex);
    } finally {
        ResourceManager.closeConnection(con);
    }}

    public double totalOrderPriceShipper(int shipperId) throws WarehouseException {
    Connection con = null;
    try {
        con = ResourceManager.getConnection();
        con.setAutoCommit(false);

        
        ArrayList<Orders> orders = OrdersDao.getInstance().findForShipper(con, shipperId);

        double totalOrderPrice = 0.0;

        
        for (Orders order : orders) {
            OrderDetails orderDetails = OrderDetailsDao.getInstance().findForOrder(con, order.getOrderId());
                Products product = ProductsDao.getInstance().find(con, orderDetails.getProduct().getProductId());
                totalOrderPrice += orderDetails.getQuantity() * product.getPricePerUnit();
            
        }
        con.commit();
        return totalOrderPrice;
    } catch (SQLException ex) {
        ResourceManager.rollbackTransactions(con);
        throw new WarehouseException("Failed to calculate total order price for shipper with id "+shipperId, ex);
    } finally {
        ResourceManager.closeConnection(con);
    }
}



    
}

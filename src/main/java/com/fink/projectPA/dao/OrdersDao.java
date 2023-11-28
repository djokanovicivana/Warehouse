/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.dao;

import com.fink.projectPA.data.Customers;
import com.fink.projectPA.data.Employees;
import com.fink.projectPA.data.Orders;
import com.fink.projectPA.data.Shippers;
import com.fink.projectPA.exception.WarehouseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class OrdersDao {
    private static final OrdersDao instance=new OrdersDao();

    public OrdersDao() {
    }
    
    public static OrdersDao getInstance(){
        return instance;
    }
    public void create(Connection con, Orders order) throws SQLException, WarehouseException{
        PreparedStatement ps=null;
        String sql="INSERT INTO Orders (OrderId, OrderDate, CustomerId, EmployeeId, ShipperId) VALUES(?,?,?,?,?)";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1,order.getOrderId());
            ps.setDate(2, order.getOrderDate());
            Customers customer=CustomersDao.getInstance().find(con, order.getCustomer().getCustomerId());
            if(customer==null){
                throw new WarehouseException("Customer "+customer +"doesn't exist in database");
            }
            ps.setInt(3, customer.getCustomerId());
            Employees employee=EmployeesDao.getInstance().find(con, order.getEmployee().getEmployeeId());
            if(employee==null){
                throw new WarehouseException("Employee "+employee +"doesn't exist in database");
            }
            ps.setInt(4, employee.getEmployeeId());
            Shippers shipper=ShippersDao.getInstance().find(con, order.getShipper().getShipperId());
            if(shipper==null){
                throw new WarehouseException("Shipper "+shipper +"doesn't exist in database");
            }
            ps.setInt(5, shipper.getShipperId());
            ps.executeUpdate();
        }finally{
            ResourceManager.closeResources(null, ps);
    }}
    public void update(Connection con, Orders order) throws SQLException{
        PreparedStatement ps=null;
        String sql="UPDATE Orders SET OrderDate=?, CustomerId=?, EmployeeId=?, ShipperId=? WHERE OrderId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setDate(1, order.getOrderDate());
            ps.setInt(2, order.getCustomer().getCustomerId());
            ps.setInt(3, order.getEmployee().getEmployeeId());
            ps.setInt(4, order.getShipper().getShipperId());
            ps.setInt(5, order.getOrderId());
            ps.executeUpdate();
        }
        finally{
            ResourceManager.closeResources(null, ps);
        }  
    }
    public Orders find(Connection con, int orderId) throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        Orders order;
        String sql="SELECT * FROM Orders WHERE OrderId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1,orderId);
            rs=ps.executeQuery();
            if(rs.next()){
                Customers customer=CustomersDao.getInstance().find(con, rs.getInt("CustomerId"));
                Employees employee=EmployeesDao.getInstance().find(con, rs.getInt("EmployeeId"));
                Shippers shipper=ShippersDao.getInstance().find(con, rs.getInt("ShipperId"));
                order=new Orders(rs.getInt("OrderId"),rs.getDate("OrderDate"), customer, employee, shipper);
            }else{
                return null;
            }
        }finally{
            ResourceManager.closeResources(rs,ps);
    }
        return order;}
    
    public ArrayList<Orders> findAll(Connection con) throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<Orders> orders=new ArrayList<>();
        String sql="SELECT * FROM Orders";
        try{
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Customers customer=CustomersDao.getInstance().find(con, rs.getInt("CustomerId"));
                Employees employee=EmployeesDao.getInstance().find(con, rs.getInt("EmployeeId"));
                Shippers shipper=ShippersDao.getInstance().find(con, rs.getInt("ShipperId"));
                orders.add(new Orders(rs.getInt("OrderId"),rs.getDate("OrderDate"), customer, employee, shipper));
            }
        }
        finally{
            ResourceManager.closeResources(rs, ps);
        }
        return orders;
    }
    public void delete(Connection con, int orderId)throws SQLException{
        PreparedStatement ps=null;
        PreparedStatement ps1=null;
        String sql="DELETE FROM Orders WHERE OrderId=?";
        try{
            //OrderDetailsDao.getInstance().deleteWithOrder(con, order);
            ps=con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.executeUpdate();
        
        }
        finally{
            ResourceManager.closeResources(null, ps);
        }
    }
    public void deleteWithCustomer(Connection con, int customerId) throws SQLException{
        PreparedStatement ps=null;
        String sql="DELETE FROM Orders WHERE CustomerId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.executeUpdate();
        }
        finally{
            ResourceManager.closeResources(null, ps);}
    }
     public void deleteWithEmployee(Connection con, int employeeId) throws SQLException{
        PreparedStatement ps=null;
        String sql="DELETE FROM Orders WHERE EmployeeId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ps.executeUpdate();
        }
        finally{
            ResourceManager.closeResources(null, ps);}
    }
     public void deleteWithShipper(Connection con, int shipperId) throws SQLException{
        PreparedStatement ps=null;
        String sql="DELETE FROM Orders WHERE ShipperId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, shipperId);
            ps.executeUpdate();
        }
        finally{
            ResourceManager.closeResources(null, ps);}
    }
}

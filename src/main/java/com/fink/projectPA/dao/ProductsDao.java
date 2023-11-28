/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.dao;

import com.fink.projectPA.data.Products;
import com.fink.projectPA.data.Suppliers;
import com.fink.projectPA.data.Suppliers;
import com.fink.projectPA.exception.WarehouseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class ProductsDao {
     private static final ProductsDao instance = new ProductsDao();

    private ProductsDao() {
    }

    public static ProductsDao getInstance() {
        return instance;
    }
    public void create(Connection con, Products product) throws SQLException, WarehouseException{
        PreparedStatement ps=null;
        String sql="INSERT INTO Products (ProductId, ProductName,ProductCategory, PricePerUnit, SupplierId) VALUES (?,?,?,?)";
        try{
           ps=con.prepareStatement(sql);
           ps.setInt(1, product.getProductId());
           ps.setString(2, product.getProductName());
           ps.setString(3, product.getProductName());
           ps.setString(4, product.getProductCategory());
           ps.setDouble(5, product.getPricePerUnit());
           Suppliers supplier=SuppliersDao.getInstance().find(con, product.getSupplier().getSupplierId());
           if(supplier==null){
               throw new WarehouseException("Supplier" + product.getSupplier() + "doesn't exist in database");
           }
           ps.setInt(6, supplier.getSupplierId());
           ps.executeUpdate();
        } finally {
            ResourceManager.closeResources(null, ps);
        
    }
    }
    public void delete(Connection con, Products product) throws SQLException {
        PreparedStatement ps = null;
        String sql="DELETE FROM Products WHERE ProductId=?";  
        try {
            OrderDetailsDao.getInstance().delete(con, product);
            ps = con.prepareStatement(sql);
            ps.setInt(1, product.getProductId());
            ps.executeUpdate();

        } finally {
            ResourceManager.closeResources(null, ps);
        }
    }
     public void update(Connection con, Products product) throws SQLException {
        PreparedStatement ps = null;
        String sql="UPDATE Products SET ProductName=?, SupplierId=?, ProductCategory=?, PricePerUnit=? WHERE ProductId=?";
        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getSupplier().getSupplierId());
            ps.setString(3, product.getProductCategory());
            ps.setDouble(4, product.getPricePerUnit());
            ps.setInt(5, product.getProductId());
            ps.executeUpdate();
        } finally {
            ResourceManager.closeResources(null, ps);
        }
    }
   public Products find(Connection con, int productId) throws SQLException{
       PreparedStatement ps=null;
       ResultSet rs=null;
       Products product;
       String sql="SELECT * FROM Products WHERE ProductId=?";
       try{
           ps=con.prepareStatement(sql);
           ps.setInt(1, productId);
           rs=ps.executeQuery();
           if(rs.next()){
               Suppliers supplier=SuppliersDao.getInstance().find(con, rs.getInt("ProductId"));
               product=new Products(rs.getInt("ProductId"), rs.getString("ProductName"), supplier, rs.getString("ProductCategory"), rs.getDouble("PricePerUnit"));
           }else{
               return null;
           }}
           finally{
                   ResourceManager.closeResources(rs,ps);
                   }
       return product;
   }
   private ArrayList<Products> findAll(Connection con) throws SQLException{
       PreparedStatement ps=null;
       ResultSet rs=null;
       ArrayList<Products> products=new ArrayList<>();
       String sql="SELECT * FROM Products";
       try{
           ps=con.prepareStatement(sql);
           rs=ps.executeQuery();
           while(rs.next()){
                Suppliers supplier=SuppliersDao.getInstance().find(con, rs.getInt("ProductId"));
                products.add(new Products(rs.getInt("ProductId"), rs.getString("ProductName"), supplier, rs.getString("ProductCategory"), rs.getDouble("PricePerUnit")));
           } 
       }
       finally{
           ResourceManager.closeResources(rs, ps);
       }
       return products;
       
   }
    
}

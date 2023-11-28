/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.service;

import com.fink.projectPA.dao.CustomersDao;
import com.fink.projectPA.dao.ResourceManager;
import com.fink.projectPA.data.Customers;
import com.fink.projectPA.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class CustomerService {
     private static final CustomerService instance = new CustomerService();

    private CustomerService() {
    }

    public static CustomerService getInstance() {
        return instance;
    }
     public void addNewCustomer(Customers customer) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourceManager.getConnection(); //konekcije se kreiraju u servisnim klasama, pa se onda prosledjuje dao klasama

            con.setAutoCommit(false);

            CustomersDao.getInstance().create(con, customer);

            con.commit();
        } catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new customer " + customer, ex);
        } finally {
            ResourceManager.closeConnection(con);
        }
    }
     public Customers findCustomer(int customerId) throws WarehouseException{
         Connection con=null;
         try{
             con=ResourceManager.getConnection();
             return CustomersDao.getInstance().find(con, customerId);
     }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find customer with id " + customerId, ex);}
     finally{
             ResourceManager.closeConnection(con);} 
}
     public ArrayList<Customers> findAllCustomers() throws WarehouseException{
         Connection con=null;
         try{
             con=ResourceManager.getConnection();
             return CustomersDao.getInstance().findAll(con);
         }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all customers", ex);}
         finally{
             ResourceManager.closeConnection(con);
         }
     }
      public void deleteCustomer(int customerId) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourceManager.getConnection();
            con.setAutoCommit(false);

            Customers customer = CustomersDao.getInstance().find(con,customerId);
            if (customer != null) {
                CustomersDao.getInstance().delete(con, customerId);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete customer with id " + customerId, ex);
        } finally {
            ResourceManager.closeConnection(con);
        }
    }
      public void updateCustomer(Customers customer) throws WarehouseException{
          Connection con=null;
          try{
              con=ResourceManager.getConnection();
              con.setAutoCommit(false);
              if(customer!=null){
              CustomersDao.getInstance().update(con, customer);}
              con.commit();
          }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update customer " + customer, ex);
      }finally{
              ResourceManager.closeConnection(con);
          }}
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.service;

import com.fink.projectPA.dao.EmployeesDao;
import com.fink.projectPA.dao.OrdersDao;
import com.fink.projectPA.dao.ResourceManager;
import com.fink.projectPA.data.Employees;
import com.fink.projectPA.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class EmployeeService {
    
    private static final EmployeeService instance = new EmployeeService();

    private EmployeeService() {
    }

    public static EmployeeService getInstance() {
        return instance;
    } 
    public void addNewEmployee(Employees employee) throws WarehouseException{
        Connection con=null;
        try{
           con=ResourceManager.getConnection();
           con.setAutoCommit(false);
           EmployeesDao.getInstance().create(con, employee);
           con.commit();
           
        }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to create employee " + employee, ex);
        
    }finally{
            ResourceManager.closeConnection(con);
        }
}
      public Employees findEmployee(int employeeId) throws WarehouseException{
         Connection con=null;
         try{
             con=ResourceManager.getConnection();
             return EmployeesDao.getInstance().find(con, employeeId);
     }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find employee with id " + employeeId, ex);}
     finally{
             ResourceManager.closeConnection(con);} 
}
     public ArrayList<Employees> findAllEmployees() throws WarehouseException{
         Connection con=null;
         try{
             con=ResourceManager.getConnection();
             return EmployeesDao.getInstance().findAll(con);
         }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all employees", ex);}
         finally{
             ResourceManager.closeConnection(con);
         }
     }
     public void deleteEmployee(int employeeId) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourceManager.getConnection();
            con.setAutoCommit(false);

            Employees employee = EmployeesDao.getInstance().find(con,employeeId);
            if (employee != null) {
                EmployeesDao.getInstance().delete(con, employeeId);
            }
            OrdersDao.getInstance().deleteWithEmployee(con, employeeId);

            con.commit();
        } catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete employee with id " + employeeId, ex);
        } finally {
            ResourceManager.closeConnection(con);
        }
    }
      public void updateEmployee(Employees employee) throws WarehouseException{
          Connection con=null;
          try{
              con=ResourceManager.getConnection();
              con.setAutoCommit(false);
             // if(employee!=null){
              EmployeesDao.getInstance().update(con, employee);
              con.commit();
          }catch (SQLException ex) {
            ResourceManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update employee " + employee, ex);
      }finally{
              ResourceManager.closeConnection(con);
          }}
}

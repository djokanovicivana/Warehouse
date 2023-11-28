/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.dao;

import com.fink.projectPA.data.Employees;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */
public class EmployeesDao {
    private static final EmployeesDao instance = new EmployeesDao();
    
    private EmployeesDao() {
    }

    public static EmployeesDao getInstance() {
        return instance;
    }
    public void create(Connection con, Employees employee) throws SQLException{
        PreparedStatement ps=null;
        String sql="INSERT INTO Employees (LastName, FirstName, BirthDate) VALUES (?,?,?)";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1, employee.getLastName());
            ps.setString(2, employee.getFirstName());
            ps.setDate(3, employee.getBirthDate());
            ps.executeUpdate();
        }
        finally{
            ResourceManager.closeResources(null, ps);
        }
    }
    public void update(Connection con, Employees employee) throws SQLException{
        PreparedStatement ps=null;
        String sql="UPDATE Employees SET LastName=?, FirstName=?, BirthDate=? WHERE EmployeeId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1, employee.getLastName());
            ps.setString(2, employee.getFirstName());
            ps.setDate(3, employee.getBirthDate());
            ps.setInt(4, employee.getEmployeeId());
            ps.executeUpdate();
        }
        finally{
            ResourceManager.closeResources(null, ps);
        } 
    }
      public void delete(Connection con, int employeeId) throws SQLException{
        PreparedStatement ps=null;
        String sql="DELETE FROM Employees WHERE EmployeeId=?";
        try{
           ps=con.prepareStatement(sql);
           ps.setInt(1, employeeId);
           ps.executeUpdate();
        }
        finally{
            ResourceManager.closeResources(null, ps);
        }
    }
     public Employees find(Connection con, int employeeId)throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        Employees employee=null;
        String sql="SELECT * FROM Employees WHERE EmployeeId=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, employeeId);
            rs=ps.executeQuery();
            if(rs.next()){
                employee=new Employees(rs.getInt("EmployeeId"), rs.getString("LastName"), rs.getString("FirstName"), rs.getDate("BirthDate"));
            }else{
                return null;
            }
        }
        finally{
            ResourceManager.closeResources(rs,ps);
        }
        return employee;
    }
        public ArrayList<Employees> findAll(Connection con) throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList <Employees> employees=new ArrayList<Employees>();
        String sql="SELECT * FROM Customers";
        try{
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                employees.add(new Employees(rs.getInt("EmployeeId"), rs.getString("LastName"), rs.getString("FirstName"), rs.getDate("BirthDate"))); 
            }}
            finally{
                    ResourceManager.closeResources(rs,ps);
                    }
        return employees;
        
    }
    
}

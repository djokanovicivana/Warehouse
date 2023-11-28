/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.data;

import java.sql.Date;

/**
 *
 * @author Ivana
 */
public class Employees {
    private int employeeId=-1;
    private String lastName;
    private String firstName;
    private Date birthDate;

    public Employees(String lastName, String firstName, Date birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
    }
     public Employees(int employeeId,String lastName, String firstName, Date birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Employees{" + "employeeId=" + employeeId + ", lastName=" + lastName + ", firstName=" + firstName + ", birthDate=" + birthDate + '}';
    }
    
    
    
    
    
    
}

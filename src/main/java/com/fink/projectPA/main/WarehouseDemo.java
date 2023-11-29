/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.main;

import com.fink.projectPA.data.Customers;
import com.fink.projectPA.exception.WarehouseException;
import com.fink.projectPA.service.AdvancedService;
import com.fink.projectPA.service.CustomerService;

/**
 *
 * @author Ivana
 */
public class WarehouseDemo {
     private static final CustomerService customerService = CustomerService.getInstance();
     public static void main(String[] args) throws Exception {
        addTestCustomers();
       // System.out.print(findCustomer());
       // updateCustomer();
       System.out.print(AdvancedService.getInstance().findProductsForSupplier(4));
    
    }
      private static void addTestCustomers() throws WarehouseException {
        customerService.addNewCustomer(new Customers("Ivana", "Marija", "Bulevar Kraljice Marije", "Kragujevac", "34000", "Srbija"));
        customerService.addNewCustomer(new Customers("Marija", "Ivana", "Kneza Mihaila", "Kragujevac", "34000", "Srbija"));
     }
       private static Customers findCustomer() throws WarehouseException {
        return customerService.findCustomer(1);
       }
        private static void updateCustomer() throws WarehouseException{
            customerService.updateCustomer(new Customers(2, "Marija", "Andjela", "Kneza Mihaila", "Kragujevac", "34000", "Srbija"));
        }
}
    


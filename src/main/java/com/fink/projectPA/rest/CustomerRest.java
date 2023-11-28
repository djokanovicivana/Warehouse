/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.rest;

import com.fink.projectPA.data.Customers;
import com.fink.projectPA.exception.WarehouseException;
import com.fink.projectPA.service.CustomerService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 *
 * @author Ivana
 */

@Path("customer")
public class CustomerRest {
    private final CustomerService customerService = CustomerService.getInstance();
    @GET()
    @Path("/all")
    public ArrayList<Customers> getAllCustomers() throws WarehouseException{
        return customerService.findAllCustomers();
    }
    @GET()
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customers getCustomer(@PathParam("customerId") int customerId) throws WarehouseException{
        return customerService.findCustomer(customerId);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customers customer) throws WarehouseException{
            customerService.addNewCustomer(customer);
            return Response.ok().build();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(Customers customer) throws WarehouseException {
            customerService.updateCustomer(customer);
            return Response.ok().build();
    }
    @DELETE
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("customerId") int customerId) throws WarehouseException {
            customerService.deleteCustomer(customerId);
            return Response.ok().build();
    }
    
    
    
}

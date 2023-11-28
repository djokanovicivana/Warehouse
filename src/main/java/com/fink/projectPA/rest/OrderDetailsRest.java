/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.rest;

import com.fink.projectPA.data.Employees;
import com.fink.projectPA.data.OrderDetails;
import com.fink.projectPA.data.Shippers;
import com.fink.projectPA.exception.WarehouseException;
import com.fink.projectPA.service.EmployeeService;
import com.fink.projectPA.service.OrderDetailsService;
import com.fink.projectPA.service.ShipperService;

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

@Path("orderDetails")
public class OrderDetailsRest {
    private final OrderDetailsService orderDetailsService = OrderDetailsService.getInstance();
    
    @GET()
    @Path("/all")
    public ArrayList<OrderDetails> getAllOrderDetails() throws WarehouseException{
        return orderDetailsService.findAllOrderDetails();
    }
    @GET()
    @Path("/{orderDetailsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDetails getOrderDetails(@PathParam("orderDetailsId") int orderDetailsId) throws WarehouseException{
        return orderDetailsService.findOrderDetails(orderDetailsId);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrderDetails(OrderDetails orderDetails) throws WarehouseException{
            orderDetailsService.addNewOrderDetails(orderDetails);
            return Response.ok().build();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrderDetails(OrderDetails orderDetails) throws WarehouseException {
            orderDetailsService.updateOrderDetails(orderDetails);
            return Response.ok().build();
    }
    @DELETE
    @Path("/{orderDetailsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrderDetails(@PathParam("orderDetailsId") int orderDetailsId) throws WarehouseException {
            orderDetailsService.deleteOrderDetails(orderDetailsId);
            return Response.ok().build();
    }
    
    
    
}

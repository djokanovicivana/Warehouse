/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.rest;

import com.fink.projectPA.data.Employees;
import com.fink.projectPA.data.Orders;
import com.fink.projectPA.data.Shippers;
import com.fink.projectPA.exception.WarehouseException;
import com.fink.projectPA.service.AdvancedService;
import com.fink.projectPA.service.EmployeeService;
import com.fink.projectPA.service.OrderService;
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

@Path("order")
public class OrderRest {
    private final OrderService orderService = OrderService.getInstance();
    
    @GET()
    @Path("/all")
    public ArrayList<Orders> getAllOrders() throws WarehouseException{
        return orderService.findAllOrders();
    }
    @GET()
    @Path("/totalPrice")
    public  double getTotalPrice() throws WarehouseException{
        return AdvancedService.getInstance().totalOrderPrice();
    }
    @GET()
    @Path("/totalPriceCustomer/{customerId}")
    public  double getTotalPriceCustomer(@PathParam("customerId") int customerId) throws WarehouseException{
        return AdvancedService.getInstance().totalOrderPriceCustomer(customerId);
    }
     @GET()
    @Path("/totalPriceShipper/{shipperId}")
    public  double getTotalPriceShipper(@PathParam("shipperId") int shipperId) throws WarehouseException{
        return AdvancedService.getInstance().totalOrderPriceShipper(shipperId);
    }
    @GET()
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Orders getOrder(@PathParam("orderId") int orderId) throws WarehouseException{
        return orderService.findOrder(orderId);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(Orders order) throws WarehouseException{
            orderService.addNewOrder(order);
            return Response.ok().build();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrder(Orders order) throws WarehouseException {
            orderService.updateOrder(order);
            return Response.ok().build();
    }
    @DELETE
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrder(@PathParam("orderId") int orderId) throws WarehouseException {
            orderService.deleteOrder(orderId);
            return Response.ok().build();
    }
    
    
    
}

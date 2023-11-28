/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.rest;

import com.fink.projectPA.data.Employees;
import com.fink.projectPA.data.Shippers;
import com.fink.projectPA.exception.WarehouseException;
import com.fink.projectPA.service.EmployeeService;
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

@Path("shipper")
public class ShipperRest {
    private final ShipperService shipperService = ShipperService.getInstance();
    
    @GET()
    @Path("/all")
    public ArrayList<Shippers> getAllShippers() throws WarehouseException{
        return shipperService.findAllShippers();
    }
    @GET()
    @Path("/{shipperId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Shippers getShipper(@PathParam("shipperId") int shipperId) throws WarehouseException{
        return shipperService.findShipper(shipperId);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addShipper(Shippers shipper) throws WarehouseException{
            shipperService.addNewShipper(shipper);
            return Response.ok().build();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShipper(Shippers shipper) throws WarehouseException {
            shipperService.updateShipper(shipper);
            return Response.ok().build();
    }
    @DELETE
    @Path("/{shipperId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteShipper(@PathParam("shipperId") int shipperId) throws WarehouseException {
            shipperService.deleteShipper(shipperId);
            return Response.ok().build();
    }
    
    
    
}

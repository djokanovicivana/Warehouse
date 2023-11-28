/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.rest;

import com.fink.projectPA.data.Employees;
import com.fink.projectPA.data.Shippers;
import com.fink.projectPA.data.Suppliers;
import com.fink.projectPA.exception.WarehouseException;
import com.fink.projectPA.service.EmployeeService;
import com.fink.projectPA.service.ShipperService;
import com.fink.projectPA.service.SupplierService;

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

@Path("supplier")
public class SupplierRest {
    private final SupplierService supplierService = SupplierService.getInstance();
    
    @GET()
    @Path("/all")
    public ArrayList<Suppliers> getAllSuppliers() throws WarehouseException{
        return supplierService.findAllSuppliers();
    }
    @GET()
    @Path("/{supplierId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Suppliers getSupplier(@PathParam("supplierId") int supplierId) throws WarehouseException{
        return supplierService.findSupplier(supplierId);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSupplier(Suppliers supplier) throws WarehouseException{
            supplierService.addNewSupplier(supplier);
            return Response.ok().build();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSupplier(Suppliers supplier) throws WarehouseException {
            supplierService.updateSupplier(supplier);
            return Response.ok().build();
    }
    @DELETE
    @Path("/{supplierId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSupplier(@PathParam("supplierId") int supplierId) throws WarehouseException {
            supplierService.deleteSupplier(supplierId);
            return Response.ok().build();
    }
    
    
    
}

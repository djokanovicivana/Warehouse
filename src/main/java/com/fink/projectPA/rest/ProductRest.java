/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.rest;

import com.fink.projectPA.data.Employees;
import com.fink.projectPA.data.Products;
import com.fink.projectPA.data.Shippers;
import com.fink.projectPA.exception.WarehouseException;
import com.fink.projectPA.service.EmployeeService;
import com.fink.projectPA.service.ProductService;
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

@Path("product")
public class ProductRest {
    private final ProductService productService = ProductService.getInstance();
    
    @GET()
    @Path("/all")
    public ArrayList<Products> getAllProducts() throws WarehouseException{
        return productService.findAllProducts();
    }
    @GET()
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Products getProduct(@PathParam("productId") int productId) throws WarehouseException{
        return productService.findProduct(productId);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Products product) throws WarehouseException{
            productService.addNewProduct(product);
            return Response.ok().build();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(Products product) throws WarehouseException {
            productService.updateProduct(product);
            return Response.ok().build();
    }
    @DELETE
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("productId") int productId) throws WarehouseException {
            productService.deleteProduct(productId);
            return Response.ok().build();
    }
    
    
    
}

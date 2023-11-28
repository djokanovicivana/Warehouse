/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.rest;

import com.fink.projectPA.data.Employees;
import com.fink.projectPA.exception.WarehouseException;
import com.fink.projectPA.service.EmployeeService;

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

@Path("employee")
public class EmployeeRest {
    private final EmployeeService employeeService = EmployeeService.getInstance();
    
    @GET()
    @Path("/all")
    public ArrayList<Employees> getAllEmployees() throws WarehouseException{
        return employeeService.findAllEmployees();
    }
    @GET()
    @Path("/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employees getEmployee(@PathParam("employeeId") int employeeId) throws WarehouseException{
        return employeeService.findEmployee(employeeId);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employees employee) throws WarehouseException{
            employeeService.addNewEmployee(employee);
            return Response.ok().build();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employees employee) throws WarehouseException {
            employeeService.updateEmployee(employee);
            return Response.ok().build();
    }
    @DELETE
    @Path("/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("employeeId") int employeeId) throws WarehouseException {
            employeeService.deleteEmployee(employeeId);
            return Response.ok().build();
    }
    
    
    
}

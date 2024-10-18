package org.example.lab2_rest;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;
//import service.WarehouseService;
//import entities.Product;
//import entities.Category;

import java.util.List;
import java.util.NoSuchElementException;

@Path("/warehouse")
public class WarehouseResource {

    private final WarehouseService warehouseService = new WarehouseService(); // Anta att WarehouseService hanterar trådsäkerhet

    // 1. Lägg till ny produkt
    @POST
    @Path("/products")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(@Valid Product product) {
        try {
            warehouseService.addProduct(product); // Skickar vidare till WarehouseService
            return Response.status(Response.Status.CREATED).entity(product).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // 2. Hämta produkter baserat på kategori
    @GET
    @Path("/products/category/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsByCategory(@PathParam("category") String categoryName) {
        try {
            Category category = Category.valueOf(categoryName.toUpperCase()); // Omvandlar sträng till enum
            List<Product> products = warehouseService.getProductsByCategory(category);
            return Response.ok(products).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid category: " + categoryName).build();
        }
    }

    // 3. Hämta alla produkter
    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        List<Product> products = warehouseService.getAllProducts();
        return Response.ok(products).build();
    }

    // 4. Hämta en produkt baserat på ID
    @GET
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") String id) {
        try {
            Product product = warehouseService.getProductById(id);
            return Response.ok(product).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product with ID " + id + " not found").build();
        }
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entity.Country;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import com.google.gson.GsonBuilder;
import entity.City;
import facade.CountryFacade;
import java.io.StringWriter;
import java.util.Collection;
import javax.persistence.Persistence;

/**
 *
 * @author rasmus
 */
@Stateless
@Path("country")
public class CountryFacadeREST {//extends AbstractFacade<Country> {

    static CountryFacade facade = new CountryFacade(Persistence.createEntityManagerFactory("pu"));
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//    @PersistenceContext(unitName = "pu")
//    private EntityManager em;

    public CountryFacadeREST() {
//        super(Country.class);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonCountries() {
        List<Country> countries = facade.getCountries();
        GsonBuilder builder = new GsonBuilder();
        builder.setExclusionStrategies(new ExclusionStrategy() {

            @Override
            public boolean shouldSkipClass(final Class<?> arg0) {
                return false;
            }

            @Override
            public boolean shouldSkipField(final FieldAttributes f) {
                String FN = f.getName().toLowerCase();
                return !(FN.equalsIgnoreCase("name") || FN.equalsIgnoreCase("code") || FN.equalsIgnoreCase("continent") || FN.equalsIgnoreCase("capital"));
            }

        });
        Gson gsonx = builder.create();
        String result = gsonx.toJson(countries);

        return result;
    }
    
    

//    @POST
//    @Override
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void create(Country entity) {
//        super.create(entity);
//    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") String id, Country entity) {
//        super.edit(entity);
//    }
//
//    @DELETE
//    @Path("{id}")
//    public void remove(@PathParam("id") String id) {
//        super.remove(super.find(id));
//    }
//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Country find(@PathParam("id") String id) {
//        return super.find(id);
//    }
//
//    @GET
//    //@Override
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Country> findAll() {
//        return super.findAll();
//    }
//    @GET
//    @Path("{from}/{to}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Country> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }
//
//    @GET
//    @Path("count")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String countREST() {
    //return String.valueOf(super.count());
//    }
//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }
}

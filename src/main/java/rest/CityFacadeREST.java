/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.City;
import entity.Country;
import facade.CityFacade;
import facade.CountryFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
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
import static rest.CountryFacadeREST.facade;

/**
 *
 * @author rasmus
 */
@Stateless
@Path("city")
public class CityFacadeREST extends AbstractFacade<City> {
    
    static CityFacade facade = new CityFacade(Persistence.createEntityManagerFactory("pu"));
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public CityFacadeREST() {
        super(City.class);
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonCountries() {
        List<City> countries = facade.getCitiesOfCountry("den");
        GsonBuilder builder = new GsonBuilder();
        builder.setExclusionStrategies(new ExclusionStrategy() {

            @Override
            public boolean shouldSkipClass(final Class<?> arg0) {
                return false;
            }

            @Override
            public boolean shouldSkipField(final FieldAttributes f) {
                String FN = f.getName().toLowerCase();
                return !(FN.equalsIgnoreCase("name"));
            }

        });
        Gson gsonx = builder.create();
        String result = gsonx.toJson(countries);

        return result;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(City entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, City entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public City find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<City> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<City> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

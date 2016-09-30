/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.City;
import entity.Country;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author rasmus
 */
public class CityFacade {
    
    EntityManagerFactory emf;

    public CityFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //finder alle cities som hører til "cc" (country code i form af en string)
    public List<City> getCitiesOfCountry(String cc) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM City c WHERE c.CountryCode = :CountryCode");
            query.setParameter("CountryCode", cc);
            List<City> cities = query.getResultList();
            return cities;
        } finally {
            em.close();
        }
    }
    
    //ADD CITY - kræver at city objectet allerede har fået tildelt en "country code"
    public City addCity(City c) {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        }finally{
            em.close();
        }
    }
    
}

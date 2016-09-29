/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Country;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author rasmus
 */
public class CountryFacade {
    
    EntityManagerFactory emf;

    public CountryFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Country> getCountries(){
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("select c from Country c");
            List<Country> countries = query.getResultList();
            return countries;
        } finally {
            em.close();
        }
    }   
}

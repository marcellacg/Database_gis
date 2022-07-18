
package model;

import util.JPAUtil;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.Query;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class MesorregioesService {
    
    public int tipoDimensao(String nm_meso){
        int result = 0;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();       

        Query query = em.createQuery("select dimension(a.geometria) from Mesorregioes a where a.nm_meso = :nm_meso");
        query.setParameter("nm_meso", nm_meso);

        result = (int)query.getSingleResult();
        
        em.getTransaction().commit();
        em.close();
        
        return result;
    }   
    
    public String tipoPoligono(String nm_meso){
        String result = null;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();       

        Query query = em.createQuery("select astext(envelope(a.geometria)) from Mesorregioes a where a.nm_meso = :nm_meso");
        query.setParameter("nm_meso", nm_meso);

        result = (String)query.getSingleResult();
        
        em.getTransaction().commit();
        em.close();
        
        return result;
    }   
}

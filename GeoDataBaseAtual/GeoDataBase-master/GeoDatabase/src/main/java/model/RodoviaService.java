
package model;

import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.Query;
import org.locationtech.jts.io.WKTReader;

public class RodoviaService {
    
    public List<Rodovia> listarRodovias(String tipopnv){
        List<Rodovia> result = null;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();       

        Query query = em.createQuery("select ds from Rodovia ds where ds.tipopnv = :tipopnv");
        query.setParameter("tipopnv", tipopnv);
        result = query.getResultList();
        em.getTransaction().commit();
        em.close();
        
        return result;
    }
}

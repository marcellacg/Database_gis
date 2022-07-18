
package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import util.JPAUtil;

public class MicrorregioesService {
    
    public List<Municipio> listarMunicipiosDaMicrorregiao(String nm_micro){
        List<Municipio> result = null;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();       

        Query query = em.createQuery("select mb from Microrregioes ma, Municipio mb where contains(ma.geometria, mb.geometria) = true and ma.nm_micro = :nm_micro");
        query.setParameter("nm_micro", nm_micro);
        result = query.getResultList();
        em.getTransaction().commit();
        em.close();      
        
        return result;
    }
    
    public List<Municipio> listarMunicipiosExtDaMicrorregiao(String nm_micro){
        List<Municipio> result = null;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();
        
        Query query = em.createQuery("select ma from Microrregioes mb, Municipio ma where touches(mb.geometria, ma.geometria) = true and mb.nm_micro = :nm_micro");
        query.setParameter("nm_micro", nm_micro);
        result = query.getResultList();
        em.getTransaction().commit();
        em.close();
        
        
        return result;
    }
    
    public List<Microrregioes> municipiosMicrorregiaoPE(String sigla, String nome){
        List<Microrregioes> result = null;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();
        
        Query query = em.createQuery("select a from Microrregioes a, Municipio b where a.sigla = :sigla and b.nome = :nome");
        query.setParameter("sigla", sigla);
        query.setParameter("nome", nome);
        result = query.getResultList();
        em.getTransaction().commit();
        em.close();
        
        
        return result;
    }
    
    /*public String municipiosMicrorregiaoPE(String sigla){
        String result = null;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();       

        Query query = em.createQuery("select m from Microrregioes m where m.sigla = :sigla");
        query.setParameter("sigla", sigla);
        result = (String)query.getSingleResult();
        em.getTransaction().commit();
        em.close();
        
        return result;
    }*/
    
    
    
    private Geometry wktToGeometry(String wktPoint) {
        WKTReader fromText = new WKTReader();
        Geometry geom = null;
        try {
            geom = fromText.read(wktPoint);
        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktPoint);
        }
        return geom;
    }
}

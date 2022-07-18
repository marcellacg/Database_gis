package model;


import util.JPAUtil;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.Query;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class MunicipioService {
     
    //Listar municípios vizinhos de Campina Grande
     public List<Municipio> listarMunicipiosVizinhos(String nome){
        List<Municipio> result = null;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();       

        Query query = em.createQuery("select mb from Municipio ma, Municipio mb where touches(ma.geometria, mb.geometria) = true and ma.nome = :nome");//, Alert.class);
        query.setParameter("nome", nome);
        result = query.getResultList();
        em.getTransaction().commit();
        em.close();
        
        return result;
    }
     
     public String geometriaTipoMunicipio(String nome){
        String result = null;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();       

        Query query = em.createQuery("select geometrytype(mb.geometria) from Municipio mb where mb.nome = :nome");
        query.setParameter("nome", nome);
        result = (String)query.getSingleResult();
        em.getTransaction().commit();
        em.close();
        
        return result;
    }
     
     //Qual é a distância entre os municípios de João Pessoa e Guarabira?
     public double distanciaEntreMunicipios(String municipioA, String municipioB){
        double result = 0;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();       

        Query query = em.createQuery("select distance(geography(ma.geometria), geography(mb.geometria)) from Municipio ma, Municipio mb where ma.nome = :municipioA  and   mb.nome = :municipioB");
        query.setParameter("municipioA", municipioA);
        query.setParameter("municipioB", municipioB);

        result = (double)query.getSingleResult();
        
        em.getTransaction().commit();
        em.close();
        
        return result;
    }   
     
     public Boolean geometriaEmpty(String nome){
        Boolean result = null;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();       

        Query query = em.createQuery("select isempty(a.geometria) from Municipio a where a.nome = :nome");
        query.setParameter("nome", nome);
        result = (Boolean)query.getSingleResult();
        em.getTransaction().commit();
        em.close();
        
        return result;
    }

    
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
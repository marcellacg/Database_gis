
package model;

import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.Query;

public class AeroportoService {
    
    public List<Aeroporto> listarAeroportos(String municipio){
        //String municipio
        List<Aeroporto> result = null;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();       

        Query query = em.createQuery("select mb from Aeroporto mb where mb.nome_uf = :nome_uf");
        //select geometrytype(mb.geometria) from Municipio mb where mb.nome = :nome
        //SELECT municipio FROM aeroportos_2014 WHERE nome_uf = 'Santa Catarina';
        query.setParameter("nome_uf", municipio);
        result = query.getResultList();
        em.getTransaction().commit();
        em.close();
        
        return result;
    }
}


package model;

import util.JPAUtil;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.Query;

public class EstadoService {
    public List<Estado> qualRegiao(String sigla, String nome){
        List<Estado> result = null;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();       

        Query query = em.createQuery("select a from Municipio b, Estado a where a.sigla = :sigla and b.nome = :nome");
        query.setParameter("sigla", sigla);
        query.setParameter("nome", nome);
        result = query.getResultList();
        em.getTransaction().commit();
        em.close();
        
        return result;
    }
}

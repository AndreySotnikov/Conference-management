package project.Service;

import project.Entity.Conference;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by andrey on 17.07.15.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ConferenceService {

    @PersistenceContext
    protected EntityManager em;

    public Conference findOne(Integer id) {
        try {
            return em.find(Conference.class, id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Conference> findAll() {
        try {
            List<Conference> resultList = em.createQuery("select e from Conference e").getResultList();
            return resultList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void save(Conference t) {
        try {
            em.persist(t);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void remove(Integer id) {
        try {
            em.remove(findOne(id));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

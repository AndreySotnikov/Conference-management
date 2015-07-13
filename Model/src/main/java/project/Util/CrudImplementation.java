package project.Util;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by andrey on 13.07.15.
 */

@Stateless
@Local(CrudRepository.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CrudImplementation implements CrudRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public <T> T findOne(Class<T> type, Object id) {
        return em.find(type,id);
    }

    @Override
    public <T> List<T> findAll(Class<T> type) {
        String className = type.getName();
        String tableName = className.substring(className.lastIndexOf('.') + 1, className.length());
        List<T> resultList = em.createQuery(String.format("select e from %s e", tableName)).getResultList();
        return resultList;
    }

    @Override
    public <T> void save(T t) {
        em.persist(t);
    }

    @Override
    public <T> T update(Class<T> type,Object id, T t) {
        T obj = findOne(type,id);
        return em.merge(obj);
    }

    @Override
    public <T> void remove(Class<T> type,Object id) {
        em.remove(findOne(type,id));
    }
}

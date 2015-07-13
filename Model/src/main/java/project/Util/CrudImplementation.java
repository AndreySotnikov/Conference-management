package project.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by andrey on 13.07.15.
 */
public class CrudImplementation<T,PK> implements CrudRepository<T,PK> {
    private Class<T> inst = (Class<T>)(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");

    @Override
    public T findOne(PK id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.find(inst, id);
        }finally {
            if (em!=null)
                em.close();
        }
    }

    @Override
    public List<T> findAll() {
        EntityManager em = null;
        try{
            em = emf.createEntityManager();
            String className = inst.getName();
            String tableName = className.substring(className.lastIndexOf('.') + 1, className.length());
            List<T> resultList = em.createQuery(String.format("select e from %s e", tableName)).getResultList();
            return resultList;
        }finally {
            if (em!=null)
                em.close();
        }
    }

    @Override
    public void save(T t) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
        }finally {
            if (em!=null)
                em.close();
        }
    }

    @Override
    public T update(PK id, T t) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            T entity = em.merge(t);
            em.getTransaction().commit();
            return entity;
        }catch (Exception ex){
            em.getTransaction().rollback();
            return null;
        }finally {
            if (em!=null)
                em.close();
        }
    }

    @Override
    public void remove(PK id) {
        EntityManager em = null;
        try{
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(findOne(id));
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
        }finally {
            if (em!=null)
                em.close();
        }
    }
}

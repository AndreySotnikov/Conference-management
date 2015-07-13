package project.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.ParameterizedType;

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
    public T findAll() {
        return null;
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
            em.close();
        }
    }

    @Override
    public T update(PK id, T t) {
        return null;
    }

    @Override
    public T remove(PK id) {
        return null;
    }
}

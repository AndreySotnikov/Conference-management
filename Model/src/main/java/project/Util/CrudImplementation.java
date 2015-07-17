//package project.Util;
//
//import javax.ejb.Local;
//import javax.ejb.Stateless;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
///**
// * Created by andrey on 13.07.15.
// */
//
//@Stateless
//@Local(CrudRepository.class)
//@TransactionAttribute(TransactionAttributeType.REQUIRED)
//public class CrudImplementation implements CrudRepository {
//
//    @PersistenceContext
//    protected EntityManager em;
//
//    @Override
//    public <T> T findOne(Class<T> type, Object id) {
//        try {
//            return em.find(type, id);
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public <T> List<T> findAll(Class<T> type) {
//        try {
//            String className = type.getName();
//            String tableName = className.substring(className.lastIndexOf('.') + 1, className.length());
//            List<T> resultList = em.createQuery(String.format("select e from %s e", tableName)).getResultList();
//            return resultList;
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public <T> void save(T t) {
//        try {
//            em.persist(t);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public <T> T update(Class<T> type,Object id, T t) {
//        try {
//            T obj = findOne(type, id);
//            return em.merge(obj);
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public <T> void remove(Class<T> type,Object id) {
//        try {
//            em.remove(findOne(type, id));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}

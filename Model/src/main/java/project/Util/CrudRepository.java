package project.Util;

/**
 * Created by andrey on 13.07.15.
 */
public interface CrudRepository<T,PK> {
    T findOne(PK id);
    T findAll();
    T save(T t);
    T update(PK id, T t);
    T remove(PK id);
}

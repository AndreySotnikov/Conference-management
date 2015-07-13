package project.Util;

import java.util.List;

/**
 * Created by andrey on 13.07.15.
 */
public interface CrudRepository<T,PK> {
    T findOne(PK id);
    List<T> findAll();
    void save(T t);
    T update(PK id, T t);
    void remove(PK id);
}

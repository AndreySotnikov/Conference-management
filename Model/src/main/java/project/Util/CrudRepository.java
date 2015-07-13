package project.Util;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by andrey on 13.07.15.
 */

public interface CrudRepository {
    <T> T findOne(Class<T> type, Object id);
    <T> List<T> findAll(Class<T> type);
    <T> void save(T t);
    <T> T update(Class<T> type,Object id, T t);
    <T> void remove(Class<T> type, Object id);
}

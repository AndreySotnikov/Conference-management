package project.DAO;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import project.Entity.TestEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by andrey on 10.07.15.
 */
public interface TestEntityRepository extends CrudRepository<TestEntity, Integer> {

}

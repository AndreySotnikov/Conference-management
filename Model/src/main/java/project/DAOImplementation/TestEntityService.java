package project.DAOImplementation;

import project.Entity.TestEntity;
import project.Util.CrudImplementation;

/**
 * Created by andrey on 13.07.15.
 */
public class TestEntityService extends CrudImplementation<TestEntity,Integer>{
    public void save(TestEntity testEntity){
        super.save(testEntity);
    }
    public TestEntity findOne (Integer id){
        return super.findOne(id);
    }
}

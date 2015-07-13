package project.DAOImplementation;

import project.Entity.TestEntity;
import project.Util.CrudImplementation;

import java.util.List;

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

    @Override
    public List<TestEntity> findAll() {
        return super.findAll();
    }

    @Override
    public TestEntity update(Integer id, TestEntity testEntity) {
        return super.update(id, testEntity);
    }

    @Override
    public void remove(Integer id) {
        super.remove(id);
    }
}

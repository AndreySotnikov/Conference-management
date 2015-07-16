package project.Service;

import project.Entity.TestEntity;
import project.Util.CrudImplementation;

import java.util.List;

/**
 * Created by Green-L on 14.07.2015.
 */
public class TestEntityService extends CrudImplementation{

    public TestEntity update(int id, TestEntity testEntity) {
        try {
            TestEntity oldTestEntity = findOne(TestEntity.class, id);
            oldTestEntity.setName(testEntity.getName());
            return em.merge(oldTestEntity);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<TestEntity> findAll(){
        return super.findAll(TestEntity.class);
    }

    public TestEntity findOne(Integer id){
        return super.findOne(TestEntity.class, id);
    }
}

//package project.DAOImplementation;
//
//import project.Entity.TestEntity;
//import project.Util.CrudImplementation;
//
//import javax.ejb.Stateless;
//import java.util.List;
//
///**
// * Created by andrey on 13.07.15.
// */
//@Stateless
//public class TestEntityService extends CrudImplementation<TestEntity,Integer>{
//    public void save(TestEntity testEntity){
//        System.err.println("saving");
//        super.save(testEntity);
//    }
//    public TestEntity findOne (Integer id){
//        return super.findOne(id);
//    }
//
//    @Override
//    public List<TestEntity> findAll() {
//        return super.findAll();
//    }
//
//    @Override
//    public TestEntity update(Integer id, TestEntity testEntity) {
//        return super.update(id, testEntity);
//    }
//
//    @Override
//    public void remove(Integer id) {
//        super.remove(id);
//    }
//}

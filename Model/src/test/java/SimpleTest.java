//import org.junit.Before;
//import org.junit.Test;
//import project.Entity.TestEntity;
//import project.Util.CrudImplementation;
//import project.Util.CrudRepository;
//
//import javax.persistence.EntityManager;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertSame;
//import static org.mockito.Mockito.mock;
//
///**
// * Created by andrey on 10.07.15.
// */
//
//public class SimpleTest {
//    CrudImplementation crudImplementation;
//    EntityManager entityManager;
//
//    @Before
//    public void setUp() {
//        crudImplementation = new CrudImplementation();
//
//        entityManager = mock(EntityManager.class);
//        crudImplementation.setEm(entityManager);
//    }
//
//    @Test
//    public void testFindAll() {
//        crudImplementation.save(new TestEntity("12345"));
//        assertNotNull(crudImplementation.findOne(TestEntity.class,1));
//    }
//}

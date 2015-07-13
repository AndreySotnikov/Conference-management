import org.junit.Test;
import project.DAOImplementation.TestEntityService;
import project.Entity.TestEntity;

/**
 * Created by andrey on 10.07.15.
 */

public class SimpleTest {
    TestEntityService testEntityService = new TestEntityService();
    @Test
    public void Test1(){
        testEntityService.save(new TestEntity("test"));
        testEntityService.save(new TestEntity("test1"));
        System.out.println(testEntityService.findAll());
        org.junit.Assert.assertNotNull(testEntityService.findAll());
    }
}

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
        org.junit.Assert.assertNotNull(testEntityService.findOne(1));
    }
}

import org.springframework.stereotype.Component;
import project.DAO.TestEntityRepository;
import project.Entity.TestEntity;
import project.config.DataConfig;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.service.TestEntityService;

/**
 * Created by andrey on 10.07.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
public class SimpleTest {

    @Autowired
    TestEntityService testEntityRepository;

    @Test
    public void Test1(){
        testEntityRepository.add(new TestEntity("test"));
        Assert.assertNotNull(testEntityRepository.find(1));
    }
}

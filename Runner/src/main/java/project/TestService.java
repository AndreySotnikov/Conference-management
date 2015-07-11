package project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.DAO.TestEntityRepository;
import project.Entity.TestEntity;
import project.service.TestEntityService;

/**
 * Created by andrey on 11.07.15.
 */
@Service
public class TestService {
    @Autowired
    TestEntityService testEntityService;
    public void test(){
//        TestEntityService testEntityService = new TestEntityService();
        System.out.println("OK");
        testEntityService.add(new TestEntity("test"));
        System.out.println(testEntityService.find(1));
    }
}

package project;

import project.DAOImplementation.TestEntityService;
import project.Entity.TestEntity;

/**
 * Created by andrey on 13.07.15.
 */
public class Launcher {
    public static void main(String[] args) {
        TestEntityService testEntityService = new TestEntityService();
        testEntityService.save(new TestEntity("test"));
        System.out.println(testEntityService.findOne(1));
    }
}

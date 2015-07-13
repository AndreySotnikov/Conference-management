package project;


import project.DAOImplementation.TestEntityService;
import project.Entity.TestEntity;


/**
 * Created by andrey on 13.07.15.
 */
import javax.ws.rs.*;

@Path("/")
public class Launcher {
    @GET
    @Path("/test")
    public static String test() {
        TestEntityService testEntityService = new TestEntityService();
        testEntityService.save(new TestEntity("test"));
        testEntityService.save(new TestEntity("test2"));
        return testEntityService.findAll().toString();
    }
}

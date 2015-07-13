package project;

import project.Entity.TestEntity;
import project.Util.CrudImplementation;
import project.Util.CrudRepository;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.ws.rs.*;

/**
 * Created by andrey on 13.07.15.
 */
@Path("/")
@WebService
public class Rest {
    @EJB
    CrudRepository crudRepository;

    @GET
    @Path("test")
    public String test(@QueryParam("id") Integer id){
        System.err.println("Id:" + id);
        return crudRepository.findOne(TestEntity.class,id).toString();
    }

    @POST
    @Path("test")
    public String testPost(@FormParam("name") String name){
        System.err.println("Name:" + name);
        crudRepository.save(new TestEntity("hello"));
        return "OK";
    }

    @GET
    @Path("echo")
    public String echo(@QueryParam("q") String original) {
        return original;
    }
}
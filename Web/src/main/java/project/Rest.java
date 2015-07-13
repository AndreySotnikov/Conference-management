package project;

import project.Entity.TestEntity;
import project.Util.CrudRepository;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

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
    public String test(@QueryParam("q") String original){

        crudRepository.save(new TestEntity(original));
        return crudRepository.findOne(TestEntity.class,1).toString();
    }

    @GET
    @Path("echo")
    public String echo(@QueryParam("q") String original) {
        return original;
    }
}
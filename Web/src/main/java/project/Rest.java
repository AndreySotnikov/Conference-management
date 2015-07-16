package project;

import project.Entity.TestEntity;
import project.Util.CrudRepository;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * Created by andrey on 13.07.15.
 */
@Path("/")
@Stateless
public class Rest {
    @EJB
    CrudRepository crudRepository;

    @Context
    private HttpServletRequest request;
    @Context
    private HttpServletResponse response;

    @GET
    @Path("test")
    @Produces("application/json")
    public TestEntity test(@QueryParam("id") Integer id){
        System.err.println("Id:" + id);
        return crudRepository.findOne(TestEntity.class, id);
    }

    @POST
    @Path("test")
    public String testPost(MultivaluedMap<String, String> form){
        //String name = request.getParameter("name");`
        //System.err.println("Name: " + name);
        //System.err.println("Form: " + form.toString());

        String name = form.getFirst("name");
        System.err.println("From form: " + name);
        crudRepository.save(new TestEntity(name));
        return "OK";
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<TestEntity> getAll(){
        return crudRepository.findAll(TestEntity.class);
    }

    @GET
    @Path("echo")
    public String echo(@QueryParam("q") String original) {
        return original;
    }

}
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
    org.jboss.resteasy.spi.HttpResponse response;

    @GET
    @Path("test")
    @Produces("application/json")
    public TestEntity test(@QueryParam("id") Integer id){
        System.err.println("Id:" + id);
        return crudRepository.findOne(TestEntity.class, id);
    }

    @POST
    @Path("test")
    public String testPost(){
        String name = request.getParameter("name");
        System.err.println("Name:" + name);
        crudRepository.save(new TestEntity(name));
        return "OK";
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<TestEntity> getAll(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle( "Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return crudRepository.findAll(TestEntity.class);
    }

    @GET
    @Path("echo")
    public String echo(@QueryParam("q") String original) {
        return original;
    }

}
package project;

import project.Entity.TestEntity;
import project.Service.ReporterService;
import project.Util.CrudRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
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

    @EJB
    ReporterService service;

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
    public String testPost(MultivaluedMap<String, String> form){
        System.err.println(request.toString());
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
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        return crudRepository.findAll(TestEntity.class);
    }

    @GET
    @Path("echo")
    public String echo(@QueryParam("q") String original) {
        return original;
    }

}
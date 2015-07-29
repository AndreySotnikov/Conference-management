package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.Entity.Reporter;
import project.Service.ReporterService;
import project.Service.TranslationService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * Created by andrey on 14.07.15.
 */
@Path("/reporters")
@Stateless
public class ReporterController {

    @EJB
    private ReporterService service;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpResponse response;

    @GET
    @Path("test")
    public String reporterTest(){
        return  "I am reporter";
    }

    @GET
    @Path("get/{id}")
    @Produces("application/json")
    public Reporter get(@PathParam("id") String id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findOne(id);
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<Reporter> getAll(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findAll();
    }

    @POST
    @Path("insert")
    public String insert(MultivaluedMap<String, String> form){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        String login = form.getFirst("login");
        String name = form.getFirst("name");
        String email = form.getFirst("email");
        String phone = form.getFirst("phone");
        boolean busy = (form.getFirst("busy").equalsIgnoreCase("true")?true:false);
        service.save(new Reporter(login, name, email, phone, busy));
        return "OK";
    }

    @POST
    @Path("update")
    public String update(MultivaluedMap<String, String> form){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        String login = form.getFirst("login");
        String name = form.getFirst("name");
        String email = form.getFirst("email");
        String phone = form.getFirst("phone");
        boolean busy = (form.getFirst("busy").equalsIgnoreCase("true")?true:false);
        service.update(login, name, email, phone, busy);
        return "OK";
    }


    @POST
    @Path("delete")
    public String delete(MultivaluedMap<String, String> form){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        String login = form.getFirst("login");
        service.remove(login);
        return "OK";
    }
}

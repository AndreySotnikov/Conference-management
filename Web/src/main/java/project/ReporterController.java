package project;

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
    private HttpServletResponse response;

    @GET
    @Path("test")
    public String reporterTest(){
        return  "I am reporter";
    }

    @GET
    @Path("get/{id}")
    @Produces("application/json")
    public Reporter get(@PathParam("id") String id){
        return service.findOne(id);
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<Reporter> getAll(){
        return service.findAll();
    }

    @POST
    @Path("insert")
    public String insert(MultivaluedMap<String, String> form){
        String login = form.getFirst("login");
        String name = form.getFirst("name");
        String email = form.getFirst("email");
        boolean busy = (form.getFirst("busy").equalsIgnoreCase("true")?true:false);
        service.save(new Reporter(login, name, email, busy));
        return "OK";
    }

    @POST
    @Path("update")
    public String update(MultivaluedMap<String, String> form){
        String id = form.getFirst("id");
        String login = form.getFirst("login");
        String name = form.getFirst("name");
        String email = form.getFirst("email");
        boolean busy = (form.getFirst("busy").equalsIgnoreCase("true")?true:false);
        service.update(id, new Reporter(login, name, email, busy));
        return "OK";
    }


    @POST
    @Path("delete")
    public String delete(MultivaluedMap<String, String> form){
        String login = form.getFirst("login");
        service.remove(login);
        return "OK";
    }
}

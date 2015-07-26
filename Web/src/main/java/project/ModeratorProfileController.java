package project;

import project.Entity.Moderator;
import project.Entity.Room;
import project.Service.ModeratorService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * Created by nikitayakuntsev on 19.07.15.
 */


@Stateless
@Path("moderator")
public class ModeratorProfileController {

    @EJB
    private ModeratorService service;

    @Context
    private HttpServletRequest request;
    @Context
    private org.jboss.resteasy.spi.HttpResponse response;


    @GET
    @Path("profile/{login}")
    @Produces("application/json")
    public Moderator getModerator(@PathParam("login")  String login) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findOne(login);
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<Moderator> getAll(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findAll();
    }

    @POST
    @Path("add")
    public String add(MultivaluedMap<String, String> form) {
        System.err.println("moder add POST");
        String login = form.getFirst("login");
        String name = form.getFirst("name");
        String phone = form.getFirst("phone");
        String email = form.getFirst("email");
        try {
            service.save(new Moderator(login, name, email, phone));
            return "OK";
        } catch (Exception e) {
            return "NOT OK";
        }
    }

    @POST
    @Path("update")
    public String update(MultivaluedMap<String, String> form) {
        System.err.println("moder update POST");
        String login = form.getFirst("login");
        String name = form.getFirst("name");
        String phone = form.getFirst("phone");
        String email = form.getFirst("email");
        try {
            service.update(login, new Moderator(login, name, email, phone));
            return "OK";
        } catch (Exception e) {
            return "NOT OK";
        }
    }

    @POST
    @Path("delete")
    public String delete(MultivaluedMap<String, String> form) {
        String login = form.getFirst("login");
        try {
            service.remove(login);
            return "OK";
        } catch (Exception e) {
            return "NOT OK";
        }
    }

}

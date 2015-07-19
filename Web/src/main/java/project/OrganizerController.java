package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.Entity.Conference;
import project.Entity.Organizer;
import project.Service.OrganizerService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;

/**
 * Created by andrey on 19.07.15.
 */
@Path("/organizer")
@Stateless
public class OrganizerController {
    @EJB
    OrganizerService organizerService;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpResponse response;

    @Path("add")
    @POST
    public String add(MultivaluedMap<String, String> form){
        Organizer organizer = new Organizer();
        organizer.setLogin(form.getFirst("login"));
        organizer.setName(form.getFirst("name"));
        organizer.setEmail(form.getFirst("email"));
        organizer.setPhone(form.getFirst("phone"));
        organizer.setConferences(new ArrayList<Conference>());
        organizerService.save(organizer);
        return "OK";
    }

    @Path("show/{id}")
    @GET
    @Produces("application/json")
    public Organizer findOne(@PathParam("id") String login){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        return organizerService.findOne(login);
    }

    @Path("update")
    @POST
    @Produces("application/json")
    public Organizer update(MultivaluedMap<String,String> form){
        return organizerService.update(form.getFirst("login"),
                form.getFirst("name"),
                form.getFirst("email"),
                form.getFirst("phone"));
    }

    @POST
    @Path("delete")
    public String delete(MultivaluedMap<String, String> form){
        try {
            organizerService.remove(form.getFirst("login"));
            return "OK";
        }catch (Exception e){
            return "fail";
        }
    }


}

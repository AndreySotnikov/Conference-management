package project;

import project.Entity.RoomOwner;
import project.Service.RoomOwnerService;

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
 * Created by Green-L on 18.07.2015.
 */
@Path("/rowner")
@Stateless
public class RoomOwnerController {

    @EJB
    private RoomOwnerService service;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    @GET
    @Path("test")
    public String roomownerTest(){
        return  "I am room-owner";
    }

    @GET
    @Path("get")
    @Produces("application/json")
    public RoomOwner get(@QueryParam("id") String id){
        return service.findOne(id);
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<RoomOwner> getAll(){
        return service.findAll();
    }

    @POST
    @Path("insert")
    public String insert(MultivaluedMap<String, String> form){
        String login = form.getFirst("login");
        String name = form.getFirst("name");
        String email = form.getFirst("email");
        String phone = form.getFirst("phone");
        service.save(new RoomOwner(login, name, email, phone));
        return "OK";
    }

    @POST
    @Path("update")
    public String update(MultivaluedMap<String, String> form){
        String id = form.getFirst("id");
        String login = form.getFirst("login");
        String name = form.getFirst("name");
        String email = form.getFirst("email");
        String phone = form.getFirst("phone");
        service.update(id, new RoomOwner(login, name, email, phone));
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

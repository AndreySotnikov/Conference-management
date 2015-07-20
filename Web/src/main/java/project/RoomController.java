package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.Entity.Room;
import project.Entity.RoomOwner;
import project.Service.RoomOwnerService;
import project.Service.RoomService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * Created by nikitayakuntsev on 17.07.15.
 */


@Path("/room")
@Stateless
public class RoomController {
    @EJB
    private RoomService service;

    @EJB
    private RoomOwnerService roomOwnerService;

    @Context
    private HttpServletRequest request;
    @Context
    HttpResponse response;


    @GET
    @Path("all")
    @Produces("application/json")
    public List<Room> getAll(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findAll();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Room getRoom(@PathParam("id") Integer id) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findOne(id);
    }


    @POST
    @Path("add")
    public String add(MultivaluedMap<String, String> form) {
        System.err.println(request.toString());

        Integer number = Integer.getInteger(form.getFirst("number"));
        Integer capacity = Integer.getInteger(form.getFirst("capacity"));
        RoomOwner r = roomOwnerService.findOne(form.getFirst("roomownerid"));
        //TODO probably GENERATOR
        try {
            service.save(number, new Room(number, capacity, r));
            System.err.println("Room added: " + number);
            return "OK";
        } catch (Exception e) {
            return "NOT OK";
        }
    }

    @POST
    @Path("delete")
    public String delete(MultivaluedMap<String, String> form) {
        Integer number = Integer.getInteger(form.getFirst("number"));
        try {
            service.remove(number);
            return "OK";
        } catch (Exception e) {
            return "NOT OK";
        }
    }


    @POST
    @Path("update")
    public String update(MultivaluedMap<String, String> form) {
        System.err.println(request.toString());

        Integer number = Integer.getInteger(form.getFirst("number"));
        Integer capacity = Integer.getInteger(form.getFirst("capacity"));
        Boolean isBusy = Boolean.getBoolean(form.getFirst("isBusy"));
        RoomOwner r = roomOwnerService.findOne(form.getFirst("roomownerid"));
        try {
            service.update(number, new Room(number, capacity, r, isBusy));
            System.err.println("Room updated: " + number);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "NOT OK";
        }
    }


}

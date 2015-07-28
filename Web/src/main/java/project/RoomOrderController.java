package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.Entity.Room;
import project.Entity.RoomOrder;
import project.Entity.RoomOwner;
import project.Entity.Speech;
import project.Service.RoomOrderService;
import project.Service.RoomOwnerService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Date;
import java.util.List;

/**
 * Created by nikitayakuntsev on 17.07.15.
 */


@Path("/roomorder")
@Stateless
public class RoomOrderController {
    @EJB
    private RoomOrderService service;

    @EJB
    private RoomOwnerService roomOwnerService;

    @Context
    private HttpServletRequest request;
    @Context
    private HttpResponse response;


    @GET
    @Path("all")
    @Produces("application/json")
    public List<RoomOrder> getAll(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findAll();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public RoomOrder getRoom(@PathParam("id") Integer id) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findOne(id);
    }


    @POST
    @Path("add")
    public String add(MultivaluedMap<String, String> form) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        Integer speechId = Integer.valueOf(form.getFirst("speechId"));
        Integer roomId = Integer.valueOf(form.getFirst("roomId"));
        Date dateFrom = Date.valueOf(form.getFirst("dateFrom"));
        Date dateTo = Date.valueOf(form.getFirst("dateTo"));
        String res = (service.add(speechId, roomId, dateFrom, dateTo)?"OK":"NOT OK");
        return res;
    }

    @POST
    @Path("delete")
    public String delete(MultivaluedMap<String, String> form) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        Integer roomOrderId = Integer.valueOf(form.getFirst("rorderId"));
        service.remove(roomOrderId);
        return "OK";
    }


    @POST
    @Path("update")
    public String update(MultivaluedMap<String, String> form) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        Integer roomOrderId = Integer.valueOf(form.getFirst("roomOrderId"));
        Speech speech = service.findOne(Speech.class, Integer.valueOf(form.getFirst("speechId")));
        Room room = service.findOne(Room.class, Integer.valueOf(form.getFirst("roomId")));
        Date dateFrom = Date.valueOf(form.getFirst("dateFrom"));
        Date dateTo = Date.valueOf(form.getFirst("dateTo"));
        String res = (service.update(roomOrderId, new RoomOrder(room, speech, dateFrom, dateTo))!=null?"OK":"NOT OK");
        return res;
    }


}

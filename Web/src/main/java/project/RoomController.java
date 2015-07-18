package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.Entity.Room;


import project.Entity.TestEntity;
import project.Service.RoomService;
import project.Service.TestEntityService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by nikitayakuntsev on 17.07.15.
 */


@Stateless
@Path("/room")
public class RoomController {
    @EJB
    private RoomService service;

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
}

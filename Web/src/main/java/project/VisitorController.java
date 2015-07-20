package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.Entity.Visitor;
import project.Service.VisitorService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by nikita on 20.07.15.
 */
@Path("Visitor")
public class VisitorController {
    @Context
    private HttpServletRequest request;
    @Context
    private HttpResponse response;
    @EJB
    private VisitorService visitorService;

    @GET
    @Path("info")
    @Produces("application/json")
    public Visitor visitorInfo(@QueryParam("login") String id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        return visitorService.findOne(id);
    }

    @POST
    @Path("info")
    public String update(MultivaluedMap<String, String> form){
        try {
            Visitor visitor = visitorService.findOne(form.getFirst("login"));
            visitor.setEmail(form.getFirst("email"));
            visitor.setPhone(form.getFirst("phone"));
            visitor.setName(form.getFirst("name"));
            visitorService.update(form.getFirst("login"),visitor);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @POST
    @Path("delete")
    public String deleteVisitor(MultivaluedMap<String, String> form){
        throw new NotImplementedException();
    }
}

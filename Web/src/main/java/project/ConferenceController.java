package project;

import project.Entity.Conference;
import project.Service.ConferenceService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by andrey on 17.07.15.
 */

@Path("/conference")
public class ConferenceController {
    @EJB
    ConferenceService conferenceService;

    @Context
    private HttpServletRequest request;
    @Context
    org.jboss.resteasy.spi.HttpResponse response;

    @Path("all")
    @GET
    @Produces("application/json")
    public List<Conference> getAll(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return conferenceService.findAll();
    }
}

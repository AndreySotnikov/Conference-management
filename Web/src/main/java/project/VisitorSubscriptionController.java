package project;

import project.Entity.Conference;
import project.Entity.Speech;
import project.Service.VisitorService;

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
@Path("/subscribe")
public class VisitorSubscriptionController {

    @EJB
    private VisitorService service;

    @Context
    private HttpServletRequest request;
    @Context
    private org.jboss.resteasy.spi.HttpResponse response;


    @POST
    @Path("{confId}/{speechId}")
    public String subscribeToSpeech(@PathParam("confId") Integer conferenceId, @PathParam("speechId") Integer spId, MultivaluedMap<String, String> form) {
        Integer speechId = Integer.valueOf(form.getFirst("speechId"));
        if (!spId.equals(speechId))
            return "Wrong parameters!";
        else {
            String visitorLogin = request.getUserPrincipal().getName();
            try {
                return service.registerToSpeech(visitorLogin, speechId, conferenceId) ? "OK" : "NOT OK";
            } catch (Exception e) {
                e.printStackTrace();
                return "NOT OK";
            }
        }
    }

    @POST
    @Path("{confId}/")
    public String subscribeToConference(@PathParam("confId") Integer conferenceId, MultivaluedMap<String, String> form) {
        Integer confId = Integer.valueOf(form.getFirst("confId"));
        if (!confId.equals(conferenceId)) {
            return "Wrong parameters!";
        } else {
            String visitorLogin = request.getUserPrincipal().getName();
            try {
                return service.registerToConference(visitorLogin, conferenceId) ? "OK" : "NOT OK";
            } catch (Exception e) {
                e.printStackTrace();
                return "NOT OK";
            }
        }
    }

    @GET
    @Path("speeches/{login}")
    @Produces("application/json")
    public List<Speech> getSpeechesSubscribedTo(@PathParam("login") String visitorLogin) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );

        return service.getSpeechesSubscribedTo(visitorLogin);


    }



    @GET
    @Path("conferences/{login}")
    @Produces("application/json")
    public List<Conference> getConferencesSubscribedTo(@PathParam("login") String visitorLogin) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.getConferencesSubscribedTo(visitorLogin);


    }

}

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Path("speech")
    public String subscribeToSpeech(MultivaluedMap<String, String> form) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        Integer speechId = Integer.valueOf(form.getFirst("speechId"));
        String visitorLogin = request.getUserPrincipal().getName();
        try {
            return service.registerToSpeech(visitorLogin, speechId) ? "OK" : "NOT OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "NOT OK";
        }

    }

    @POST
    @Path("conference")
    public String subscribeToConference(MultivaluedMap<String, String> form) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        Integer confId = Integer.valueOf(form.getFirst("confId"));
        String visitorLogin = request.getUserPrincipal().getName();
        try {
            return service.registerToConference(visitorLogin, confId) ? "OK" : "NOT OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "NOT OK";
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

    @GET
    @Path("check/")
    @Produces("application/json")
    public Map<String, Boolean> isVisitorSubscribedToConference(
            @QueryParam("visitor") String visitorLogin,
            @DefaultValue("-1") @QueryParam("speech") Integer speechId,
            @DefaultValue("-1") @QueryParam("conf") Integer confId) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );

        Map<String, Boolean> result = new HashMap<>();
        if (speechId != -1)
            result.put("speech", service.hasVisitorSubscribedToSpeech(visitorLogin, speechId));
        if (confId != -1)
            result.put("result", service.hasVisitorSubscribedToConference(visitorLogin, confId));
        return result;
    }



}

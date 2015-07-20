package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.Entity.Speaker;
import project.Service.SpeakerService;
import project.Service.SpeechService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by andrey on 14.07.15.
 */
@Path("/speaker")
@Stateless
public class SpeakerController {

    @Context
    private HttpServletRequest request;

    @EJB
    private SpeakerService service;
    @EJB
    private SpeechService speechService;

    @Context
    private HttpResponse response;

    @GET
    @Path("test")
    public String speakerTest(){
        return  "I am speaker";
    }

    @GET
    @Path("info")
    @Produces("application/json")
    public String speakerInfo(@QueryParam("login") String id) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        return service.findOne(id).toString();
    }

    @POST
    @Path("info")
    public String speakerInfo(MultivaluedMap<String, String> form){
        try {
            Speaker speaker = service.findOne(form.getFirst("login"));
            speaker.setName(form.getFirst("name"));
            speaker.setPhone(form.getFirst("phone"));
            speaker.setEmail(form.getFirst("email"));
            service.update(form.getFirst("login"),speaker);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }

    @POST
    @Path("delete")
    public String deleteSpeaker(MultivaluedMap<String, String> form){
        try {
            int cnt = speechService.findBySpeaker(form.getFirst("login")).size();
            if (cnt != 0) return "Error: you have speeches, you can't delete";
            service.remove(form.getFirst("login"));
        } catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
}

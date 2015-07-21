package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.Entity.Speech;
import project.Service.ConferenceService;
import project.Service.SpeakerService;
import project.Service.SpeechService;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Date;

/**
 * Created by nikita on 20.07.15.
 */
@Path("/speech")
public class SpeechController {
    @Context
    private HttpServletRequest request;
    @Context
    private HttpResponse response;

    @EJB
    private SpeechService speechService;
    @EJB
    private SpeakerService speakerService;
    @EJB
    private ConferenceService conferenceService;

    @POST
    @Path("add")
    public String addSpeech(MultivaluedMap<String, String> form){
        try {
            Speech speech = new Speech();
            speech.setTopic(form.getFirst("topic"));

            speech.setSpeaker(speakerService.findOne(request.getUserPrincipal().getName()));
            speech.setText("");
            speech.setApproved(false);
            speech.setCompleted(false);
            speech.setStartDate(Date.valueOf(form.getFirst("start")));
            speech.setConference(conferenceService.findOne(Integer.valueOf(form.getFirst("conference"))));
            speechService.save(speech);
            return "OK";
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }

    @GET
    @Path("info")
    @Produces("application/json")
    public Speech getInfo(@QueryParam("id") Integer id){
        try {
            response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", "*");
            response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
            response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
            return speechService.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("info")
    public String updateSpeech(MultivaluedMap<String, String> form){
        try {
            Speech speech  = speechService.findOne(Integer.valueOf(form.getFirst("id")));
            speech.setConference(conferenceService.findOne(Integer.valueOf(form.getFirst("conference"))));
            speech.setTopic(form.getFirst("topic"));
            speech.setSpeaker(speakerService.findOne(form.getFirst("speaker")));
            speech.setStartDate(Date.valueOf(form.getFirst("start")));
            speechService.update(form.getFirst("id"), speech);
            return "OK";
        } catch (Exception e){
            e.printStackTrace();
            return "ERROR";
        }
    }

    @POST
    @Path("delete")
    public String deleteSpeech(MultivaluedMap<String, String> form){
        try {
            speechService.remove(Integer.valueOf(form.getFirst("id")));
            return "OK";
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }

    @GET
    @Path("completed")
    public String setCompleted(@QueryParam("id") Integer id, @QueryParam("completed") Boolean completed){
        try {
            response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", "*");
            response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
            response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
            speechService.setCompleted(id, completed);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    @GET
    @Path("approve")
    public String setApproved(@QueryParam("id") Integer id, @QueryParam("completed") Boolean approved){
        try {
            response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", "*");
            response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
            response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
            speechService.setApproved(id, approved);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}

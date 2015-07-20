package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.Entity.Conference;
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
            speech.setSpeaker(speakerService.findOne(form.getFirst("speaker")));
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
    public Speech getInfo(@QueryParam("id") String id){
        try {
            return speechService.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

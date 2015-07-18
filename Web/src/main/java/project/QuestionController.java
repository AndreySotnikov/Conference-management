package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.Entity.Question;
import project.Service.ConferenceService;
import project.Service.OrganizerService;
import project.Service.QuestionService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by andrey on 18.07.15.
 */

@Path("/question")
@Stateless
public class QuestionController {
    @EJB
    QuestionService questionService;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpResponse response;

    @Path("all")
    @GET
    @Produces("application/json")
    public List<Question> findAll(@QueryParam("id") int speechId){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        return  questionService.findAllBySpeech(speechId);
    }

    @Path("moderated")
    @GET
    @Produces("application/json")
    public List<Question> findAllModerated(@QueryParam("id") int speechId){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        return  questionService.findAllFiltered(speechId, true);
    }

    @Path("moderated")
    @GET
    @Produces("application/json")
    public List<Question> findAllUnModerated(@QueryParam("id") int speechId){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        return  questionService.findAllFiltered(speechId,false);
    }

}

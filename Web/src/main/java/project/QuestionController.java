package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.Entity.Question;
import project.Service.ConferenceService;
import project.Service.OrganizerService;
import project.Service.QuestionService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
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
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return  questionService.findAllBySpeech(speechId);
    }

    @Path("show/{id}")
    @GET
    @Produces("application/json")
    public Question findOne(@PathParam("id") Integer id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return questionService.findOne(id);
    }

    @Path("moderated")
    @GET
    @Produces("application/json")
    public List<Question> findAllModerated(@QueryParam("id") int speechId){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return  questionService.findAllFiltered(speechId, true);
    }

    @Path("unmoderated")
    @GET
    @Produces("application/json")
    public List<Question> findAllUnModerated(@QueryParam("id") int speechId){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return  questionService.findAllFiltered(speechId,false);
    }

    @Path("add")
    @POST
    public String addQuestion(MultivaluedMap<String, String> form){
        try {
            questionService.addQuestion(form.getFirst("text"),
                    request.getUserPrincipal().getName(),
                    Integer.valueOf(form.getFirst("speechId")));
            return "OK";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    @Path("moderate/{id}")
    @GET
    public String moderateQuestion(@PathParam("id") Integer id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        questionService.moderate(id);
        return "OK";
    }

    @Path("answer")
    @POST
    public String addAnswer(MultivaluedMap<String, String> form){
        try {
            questionService.addAnswer(Integer.parseInt(form.getFirst("id")),form.getFirst("answer"));
            return "OK";
        }catch (Exception e){
            return "fail";
        }
    }

    @Path("upvote/{id}")
    @GET
    public String upvoteQuestion(@PathParam("id") Integer id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        try {
            return questionService.upvote(id, request.getUserPrincipal().getName())?"OK":"fail";
        }catch (Exception e){
            return "fail";
        }
    }

    @Path("delete")
    @POST
    public String deleteQuestion(MultivaluedMap<String, String> form){
        try {
            questionService.remove(Integer.parseInt(form.getFirst("id")));
            return "OK";
        }catch (Exception e){
            return "fail";
        }
    }

}

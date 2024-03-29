package project;

import project.Entity.Moderator;
import project.Entity.Room;
import project.Service.ModeratorService;
import project.Service.QuestionService;

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
@Path("/moderator")
@Stateless
public class ModerationController {
    @EJB
    private ModeratorService service;

    @EJB
    private QuestionService questionService;

    @Context
    private HttpServletRequest request;
    @Context
    private org.jboss.resteasy.spi.HttpResponse response;

    @GET
    @Path("show/{id}")
    public Moderator findOne(@PathParam("id") String id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findOne(id);
    }


    @GET
    @Path("moderators")
    @Produces("application/json")
    public List<Moderator> getAllModerators(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findAll();
    }



    @POST
    @Path("moderate")
    public String moderateQuestion(MultivaluedMap<String, String> form) {
        System.err.println("POST moderateQuestion");
        Integer questionId = Integer.getInteger(form.getFirst("question"));
        System.err.println("questionid = " + questionId);
        try {
            questionService.moderate(questionId);
            return "OK";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "NOT OK";
        }
    }



}

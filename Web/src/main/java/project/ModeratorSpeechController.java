package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.Entity.*;
import project.Service.ModeratorSpeechService;
import project.Util.CrudRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * Created by Green-L on 18.07.2015.
 */
@Path("/modspeech")
@Stateless
public class ModeratorSpeechController {

    @EJB
    private ModeratorSpeechService service;

    @EJB
    private CrudRepository crudRepository;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpResponse response;

    @GET
    @Path("test")
    public String moderatorSpeechTest(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return  "moderatorSpeech controller";
    }

    @GET
    @Path("checkspeech/{id}")
    @Produces("application/json")
    public boolean checkModSpch(@PathParam("id") Integer speechId){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        String moderator = request.getUserPrincipal().getName();
        return  service.checkModeratorQuestion(speechId, moderator);
    }

    @GET
    @Path("checkquestion/{id}")
    @Produces("application/json")
    public boolean checkModQstn(@PathParam("id") Integer questionId){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        String moderator = request.getUserPrincipal().getName();
        Question question = crudRepository.findOne(Question.class, questionId);
        Integer speechId = question.getSpeech().getId();
        return  service.checkModeratorQuestion(speechId, moderator);
    }


    @GET
    @Path("get/{id}")
    @Produces("application/json")
    public ModeratorRequestsSpeech get(@PathParam("id") Integer id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findOne(id);
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<ModeratorRequestsSpeech> getAll(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findAll();
    }

    @GET
    @Path("fswtam")
    @Produces("application/json")
    public List<Speech> findSpeechesWithoutApprovedModerators(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findSpeechesWithoutApprovedModerators();
    }

    @GET
    @Path("fswam")
    @Produces("application/json")
    public List<Speech> findSpeechesWithApprovedModerators(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findSpeechesWithApprovedModerators();
    }

    @GET
    @Path("fambs/{id}")
    @Produces("application/json")
    public List<Moderator> findApprovedModeratorsBySpeech(@PathParam("id") Integer id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findApprovedModeratorsBySpeech(id);
    }

    @GET
    @Path("fuambs/{id}")
    @Produces("application/json")
    public List<Moderator> findUnapprovedModeratorsBySpeech(@PathParam("id") Integer id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findUnapprovedModeratorsBySpeech(id);
    }

    @GET
    @Path("fasbm/{id}")
    @Produces("application/json")
    public List<Speech> findApprovedSpeechesByModerator(@PathParam("id") String id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findApprovedSpeechesByModerator(id);
    }

    @GET
    @Path("fuasbm/{id}")
    @Produces("application/json")
    public List<Speech> findUnapprovedSpeechesByModerator(@PathParam("id") String id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findUnapprovedSpeechesByModerator(id);
    }

    @GET
    @Path("rmos")
    public String registerModeratorOnSpeech(
            @QueryParam("speechId") Integer speechId, @QueryParam("moderatorId") String moderatorId){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        String res = (service.registerModeratorOnSpeech(moderatorId, speechId)?"OK":"NOT OK");
        return res;
    }

    @GET
    @Path("amos")
    public String approveModeratorOnSpeech(
            @QueryParam("speechId") Integer speechId, @QueryParam("moderatorId") String moderatorId){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        String res = (service.approveModeratorOnSpeech(moderatorId, speechId)?"OK":"NOT OK");
        return res;
    }

    @POST
    @Path("delete")
    public String delete(MultivaluedMap<String, String> form){
        Integer id = Integer.parseInt(form.getFirst("id"));
        service.remove(id);
        return "OK";
    }

}

package project;

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
    private HttpServletResponse response;

    @GET
    @Path("test")
    public String moderatorSpeechTest(){
        return  "moderatorSpeech controller";
    }

    @GET
    @Path("get/{id}")
    @Produces("application/json")
    public ModeratorRequestsSpeech get(@PathParam("id") Integer id){
        return service.findOne(id);
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<ModeratorRequestsSpeech> getAll(){
        return service.findAll();
    }

    @GET
    @Path("fswtam")
    @Produces("application/json")
    public List<Speech> findSpeechesWithoutApprovedModerators(){
        return service.findSpeechesWithoutApprovedModerators();
    }

    @GET
    @Path("fswam")
    @Produces("application/json")
    public List<Speech> findSpeechesWithApprovedModerators(){
        return service.findSpeechesWithApprovedModerators();
    }

    @GET
    @Path("fambs/{id}")
    @Produces("application/json")
    public List<Moderator> findApprovedModeratorsBySpeech(@PathParam("id") Integer id){
        return service.findApprovedModeratorsBySpeech(id);
    }

    @GET
    @Path("fuambs/{id}")
    @Produces("application/json")
    public List<Moderator> findUnapprovedModeratorsBySpeech(@PathParam("id") Integer id){
        return service.findUnapprovedModeratorsBySpeech(id);
    }

    @GET
    @Path("rmos")
    public String registerModeratorOnSpeech(
            @QueryParam("speechId") Integer speechId, @QueryParam("moderatorId") String moderatorId){
        service.registerModeratorOnSpeech(moderatorId, speechId);
        return "ok";
    }

    @GET
    @Path("amos")
    public String approveModeratorOnSpeech(
            @QueryParam("speechId") Integer speechId, @QueryParam("moderatorId") String moderatorId){
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

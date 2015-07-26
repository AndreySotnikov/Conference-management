package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.DTO.TranslationDTO;
import project.Entity.Speech;
import project.Entity.Translation;
import project.Service.TranslationService;
import project.Util.CrudRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Green-L on 18.07.2015.
 */
@Path("/trans")
@Stateless
public class TranslationController {

    @EJB
    private TranslationService service;

    @EJB
    private CrudRepository crudRepository;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpResponse response;

    @GET
    @Path("test")
    public String translationTest(){
        return  "translation controller";
    }

    @GET
    @Path("get/{id}")
    @Produces("application/json")
    public Translation get(@PathParam("id") Integer id){
        return service.findOne(id);
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<Translation> getAll(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findAll();
    }

    @POST
    @Path("insert")
    public String insert(MultivaluedMap<String, String> form){
        String text = form.getFirst("text");
        Timestamp timestamp = Timestamp.valueOf(form.getFirst("time"));
        Integer speechId = Integer.parseInt(form.getFirst("speechId"));
        service.save(new Translation(text, timestamp, crudRepository.findOne(Speech.class, speechId)));
        return "OK";
    }

    @GET
    @Path("fbs/{id}")
    @Produces("application/json")
    public List<TranslationDTO> findBySpeech(@PathParam("id") Integer speechId) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findBySpeech(speechId);
    }


    @GET
    @Path("updates")
    @Produces("application/json")
    public List<TranslationDTO> find(@QueryParam("speechId") Integer speechId,
                           @QueryParam("lastId") Integer lastId){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findUpdates(speechId, lastId);
    }

    @POST
    @Path("delete")
    public String delete(MultivaluedMap<String, String> form){
        Integer id = Integer.parseInt(form.getFirst("id"));
        service.remove(id);
        return "OK";
    }

}

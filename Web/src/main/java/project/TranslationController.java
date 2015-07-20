package project;

import project.Entity.Speech;
import project.Entity.Translation;
import project.Service.TranslationService;
import project.Util.CrudRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Date;
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
    private HttpServletResponse response;

    @GET
    @Path("test")
    public String translationTest(){
        return  "translation controller";
    }

    @GET
    @Path("get")
    @Produces("application/json")
    public Translation get(@QueryParam("id") Integer id){
        return service.findOne(id);
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<Translation> getAll(){
        return service.findAll();
    }

    @POST
    @Path("insert")
    public String insert(MultivaluedMap<String, String> form){
        String text = form.getFirst("text");
        Date time = Date.valueOf(form.getFirst("time"));
        Integer speechId = Integer.parseInt(form.getFirst("speechId"));
        service.save(new Translation(text, time, crudRepository.findOne(Speech.class, speechId)));
        return "OK";
    }

    @GET
    @Path("updates")
    @Produces("application/json")
    public List<Translation> get(@QueryParam("speechId") Integer speechId,
                           @QueryParam("lastId") Integer lastId){
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

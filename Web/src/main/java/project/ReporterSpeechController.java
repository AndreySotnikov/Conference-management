package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.Entity.Reporter;
import project.Entity.ReporterRequestsSpeech;
import project.Entity.Speech;
import project.Entity.Translation;
import project.Service.ReporterSpeechService;
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
import java.util.List;

/**
 * Created by Green-L on 18.07.2015.
 */
@Path("/repspeech")
@Stateless
public class ReporterSpeechController {

    @EJB
    private ReporterSpeechService service;

    @EJB
    private CrudRepository crudRepository;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpResponse response;

    @GET
    @Path("test")
    public String reporterSpeechTest(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return  "reporterSpeech controller";
    }

    @GET
    @Path("get/{id}")
    @Produces("application/json")
    public ReporterRequestsSpeech get(@PathParam("id") Integer id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findOne(id);
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<ReporterRequestsSpeech> getAll(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findAll();
    }

    @GET
    @Path("fswtar")
    @Produces("application/json")
    public List<Speech> findSpeechesWithoutApprovedReporters(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findSpeechesWithoutApprovedReporters();
    }

    @GET
    @Path("fswar")
    @Produces("application/json")
    public List<Speech> findSpeechesWithApprovedReporters(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findSpeechesWithApprovedReporters();
    }

    @GET
    @Path("farbs/{id}")
    @Produces("application/json")
    public List<Reporter> findApprovedReportersBySpeech(@PathParam("id") Integer id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findApprovedReportersBySpeech(id);
    }

    @GET
    @Path("fuarbs/{id}")
    @Produces("application/json")
    public List<Reporter> findUnapprovedReportersBySpeech(@PathParam("id") Integer id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return service.findUnapprovedReportersBySpeech(id);
    }

    @GET
    @Path("rros")
    public String registerReporterOnSpeech(
            @QueryParam("speechId") Integer speechId, @QueryParam("reporterId") String reporterId){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        String res = (service.registerReporterOnSpeech(reporterId, speechId)?"OK":"NOT OK");
        return res;
    }

    @GET
    @Path("aros")
    public String approveReporterOnSpeech(
            @QueryParam("speechId") Integer speechId, @QueryParam("reporterId") String reporterId){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        String res = (service.approveReporterOnSpeech(reporterId, speechId)?"OK":"NOT OK");
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

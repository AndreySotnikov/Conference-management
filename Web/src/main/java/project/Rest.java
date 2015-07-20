package project;

import project.Service.ReporterService;
import project.Util.CrudRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;


@Path("/")
@Stateless
public class Rest {

    @EJB
    CrudRepository crudRepository;

    @EJB
    ReporterService service;

    @Context
    private HttpServletRequest request;
    @Context
    private org.jboss.resteasy.spi.HttpResponse response;


    @GET
    @Path("echo")
    public String echo(@QueryParam("q") String original) {
        return original;
    }

}
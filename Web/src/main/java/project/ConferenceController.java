package project;

import org.jboss.resteasy.spi.HttpResponse;
import project.Entity.Conference;
import project.Entity.Speech;
import project.Service.ConferenceService;
import project.Service.OrganizerService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 18.07.15.
 */

@Path("/conference")
@Stateless
public class ConferenceController {
    @EJB
    ConferenceService conferenceService;

    @EJB
    OrganizerService organizerService;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpResponse response;

    @Path("all")
    @GET
    @Produces("application/json")
    public List<Conference> findAll() {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        return conferenceService.findAll();
    }

    @GET
    @Path("fcbo/{id}")
    @Produces("application/json")
    public List<Conference> findConferenceByOrganizer(@PathParam("id") String id){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return conferenceService.findAllByLogin(id);
    }

    @Path("show/{id}")
    @GET
    @Produces("application/json")
    public Conference findOne(@PathParam("id") Integer id) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        return conferenceService.findOne(id);
    }

    @Path("aspeeches/{id}")
    @GET
    @Produces("application/json")
    public List<Speech> approvedSpeechesByConference(@PathParam("id") Integer id) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        return conferenceService.approvedSpeechesByConference(id);
    }

    @Path("uaspeeches/{id}")
    @GET
    @Produces("application/json")
    public List<Speech> unapprovedSpeechesByConference(@PathParam("id") Integer id) {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        return conferenceService.unapprovedSpeechesByConference(id);
    }

    @Path("add")
    @POST
    public String add(MultivaluedMap<String, String> form) {
        System.err.println(form);
        try {
            Conference conference = new Conference();
            conference.setName(form.getFirst("name"));
            conference.setDescription(form.getFirst("description"));
            conference.setStartDate(Date.valueOf(form.getFirst("start")));
            conference.setEndDate(Date.valueOf(form.getFirst("end")));
            conference.setOrganizer(organizerService.findOne(request.getUserPrincipal().getName()));
            conference.setSpeeches(new ArrayList<Speech>());
            System.err.println(conference);
            conferenceService.save(conference);
            return "OK";
        } catch (Exception e) {
            return "fail";
        }
    }

    @Path("user")
    @GET
    @Produces("application/json")
    public List<Conference> getAllByUser() {
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        return conferenceService.findAllByLogin(request.getUserPrincipal().getName());
    }

    @Path("update/{id}")
    @POST
    public String update(@PathParam("id") Integer id, MultivaluedMap<String, String> form) {
        try {
            conferenceService.update(id,
                    form.getFirst("name"),
                    Date.valueOf(form.getFirst("start")),
                    Date.valueOf(form.getFirst("end")),
                    form.getFirst("description"));
            return "OK";
        } catch (Exception e) {
            return "fail";
        }
    }

    @Path("delete")
    @POST
    public String delete(MultivaluedMap<String, String> form){
        try {
            conferenceService.remove(Integer.parseInt(form.getFirst("id")));
            return "OK";
        }catch (Exception e){
            return "fail";
        }
    }

}

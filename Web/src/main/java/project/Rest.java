package project;

import project.Entity.*;
import project.Service.ReporterService;
import project.Util.CrudRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.jboss.resteasy.spi.HttpResponse;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * Created by andrey on 13.07.15.
 */
@Path("/")
@Stateless
public class Rest {
    @EJB
    CrudRepository crudRepository;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpResponse response;

    @Path("register")
    @POST
    public String register(MultivaluedMap<String, String> form){
        try {
            String login = form.getFirst("login");
            String password = form.getFirst("password");
            String role = form.getFirst("role");
            String name = form.getFirst("name");
            String email = form.getFirst("email");
            String phone = form.getFirst("phone");
            Users user = new Users(login, password);
            UserRoles userRoles = new UserRoles(login, role);
            crudRepository.save(user);
            crudRepository.save(userRoles);
            switch (role) {
                case "organizer":
                    crudRepository.save(new Organizer(login, name, email, phone));
                    break;
                case "moderator":
                    crudRepository.save(new Moderator(login, name, email, phone));
                    break;
                case "reporter":
                    crudRepository.save(new Reporter(login, name, email, phone));
                    break;
                case "roomOwner":
                    crudRepository.save(new RoomOwner(login, name, email, phone));
                    break;
                case "speaker":
                    crudRepository.save(new Speaker(login, name, email, phone));
                    break;
                case "visitor":
                    crudRepository.save(new Visitor(login, name, email, phone));
                    break;
                default:
                    return "fail";
            }
            return "OK";
        }catch (Exception e){
            return "fail";
        }
    }

    @GET
    @Path("logout")
    public void logout() throws ServletException {
        response.getOutputHeaders().putSingle("Cache-Control", "no-cache, no-store");
        response.getOutputHeaders().putSingle("Pragma", "no-cache");
        response.getOutputHeaders().putSingle("Expires", new java.util.Date().toString());
        if(request.getSession(false)!=null){
            request.getSession(false).invalidate();
        }
        if(request.getSession()!=null){
            request.getSession().invalidate();
        }
        request.logout();
    }

}
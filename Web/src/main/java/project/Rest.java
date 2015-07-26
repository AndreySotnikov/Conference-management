package project;

import project.Entity.*;
import project.Service.ReporterService;
import project.Service.UserRolesService;
import project.Util.CrudRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.jboss.resteasy.spi.HttpResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Arrays;


@Path("/")
@Stateless
public class Rest {

    @EJB
    CrudRepository crudRepository;

    @EJB
    UserRolesService userRolesService;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpResponse response;

    @Path("register")
    @POST
    public String register(MultivaluedMap<String, String> form){
        System.err.println("form:" + form);
        try {
            String login = form.getFirst("login");
            String password = form.getFirst("password");
            String role = form.getFirst("role");
            String name = form.getFirst("name");
            String email = form.getFirst("email");
            String phone = form.getFirst("phone");
            Users user = new Users(login, password);
            UserRoles userRoles = new UserRoles(login, role);
            System.err.println(user);
            System.err.println(userRoles);
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
        System.err.println("logout");

        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        if(request.getSession(false)!=null){
            request.getSession(false).invalidate();
        }
        if(request.getSession()!=null){
            request.getSession().invalidate();
        }
        request.logout();
    }

    @GET
    @Path("whoami")
    public String whoAmI(){
        response.getOutputHeaders().putSingle("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.getOutputHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getOutputHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        return userRolesService.whoAmI(request.getUserPrincipal().getName());
    }

}
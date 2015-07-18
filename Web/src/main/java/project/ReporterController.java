package project;

import project.Entity.Reporter;
import project.Service.ReporterService;
import project.Service.TranslationService;

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
 * Created by andrey on 14.07.15.
 */
@Path("/reporters")
@Stateless
public class ReporterController {

    @EJB
    private ReporterService service;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    @GET
    @Path("test")
    public String reporterTest(){
        return  "I am reporter";
    }

    @GET
    @Path("get")
    @Produces("application/json")
    public Reporter get(@QueryParam("id") String id){
        return service.findOne(id);
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<Reporter> getAll(){
        return service.findAll();
    }

    @POST
    @Path("insert")
    public String insert(MultivaluedMap<String, String> form){
        String login = form.getFirst("login");
        String name = form.getFirst("name");
        String email = form.getFirst("email");
        boolean busy = (form.getFirst("busy").equalsIgnoreCase("true")?true:false);
        service.save(new Reporter(login, name, email, busy));
        return "OK";
    }

    @POST
    @Path("delete")
    public String delete(MultivaluedMap<String, String> form){
        String login = form.getFirst("login");
        service.remove(login);
        return "OK";
    }



    @GET
    @Path("logout")
    public void logout() throws ServletException {
        boolean printJaasInfo = true;
        if(printJaasInfo){
            try{
                System.err.println("LogoutServlet>userPrincipalName:"+(request.getUserPrincipal()==null?"null":request.getUserPrincipal().getName()));//ybxiang
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", new java.util.Date().toString());//http://www.coderanch.com/t/541412/Servlets/java/Logout-servlet-button
        if(request.getSession(false)!=null){
            request.getSession(false).invalidate();
        }
        if(request.getSession()!=null){
            request.getSession().invalidate();//remove session.
        }
        request.logout();//JAAS log out (from servlet specification)! It is a MUST!
        if(printJaasInfo){
            try{
                System.err.println("LogoutServlet>userPrincipalName:"+(request.getUserPrincipal()==null?"null":request.getUserPrincipal().getName()));//ybxiang
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

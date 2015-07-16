package project;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

/**
 * Created by andrey on 14.07.15.
 */
@Path("/reporter")
@Stateless
public class ReporterController {

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

package project;

import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

/**
 * Created by andrey on 14.07.15.
 */
@Path("/speaker")
@Stateless
public class SpeakerController {

    @Context
    private HttpServletRequest request;


    @Context
    private HttpServletResponse response;

    @GET
    @Path("test")
    public String speakerTest(){
        return  "I am speaker";
    }

    @GET
    @Path("logout")
    public String logout() throws ServletException {
        boolean printJaasInfo = true;
        if(printJaasInfo){
            try{
                System.err.println("Logout>userPrincipalName:"+(request.getUserPrincipal()==null?"null":request.getUserPrincipal().getName()));//ybxiang
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", new java.util.Date().toString());
        if(request.getSession(false)!=null){
            request.getSession(false).invalidate();
        }
        if(request.getSession()!=null){
            request.getSession().invalidate();
        }
        request.getSession();
        request.logout();
        if(printJaasInfo){
            try{
                System.err.println("Logout>userPrincipalName:"+(request.getUserPrincipal()==null?"null":request.getUserPrincipal().getName()));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return "OK";
    }
}

//package project;
//
//import project.Entity.Speech;
//import project.Entity.Translation;
//import project.Service.TranslationService;
//import project.Util.CrudImplementation;
//
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.*;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MultivaluedMap;
//import java.util.List;
//
///**
// * Created by Green-L on 18.07.2015.
// */
//@Path("/trans")
//@Stateless
//public class TranslationController {
//
//    @EJB
//    private TranslationService service;
//
//    @EJB
//    private CrudImplementation crudImplementation;
//
//    @Context
//    private HttpServletRequest request;
//
//    @Context
//    private HttpServletResponse response;
//
//    @GET
//    @Path("test")
//    public String translationTest(){
//        return  "translation controller";
//    }
//
//    @GET
//    @Path("get")
//    @Produces("application/json")
//    public Translation get(@QueryParam("id") Integer id){
//        return service.findOne(id);
//    }
//
//    @GET
//    @Path("all")
//    @Produces("application/json")
//    public List<Translation> getAll(){
//        return service.findAll();
//    }
//
//    @POST
//    @Path("insert")
//    public String insert(MultivaluedMap<String, String> form){
//        String text = form.getFirst("text");
//        long time = Long.valueOf(form.getFirst("time"));
//        Integer speechId = Integer.parseInt(form.getFirst("speechId"));
//        service.save(new Translation(text, time, crudImplementation.findOne(Speech.class, speechId)));
//        return "OK";
//    }
//
//    @POST
//    @Path("delete")
//    public String delete(MultivaluedMap<String, String> form){
//        Integer id = Integer.parseInt(form.getFirst("login"));
//        service.remove(id);
//        return "OK";
//    }
//
//    @GET
//    @Path("logout")
//    public void logout() throws ServletException {
//        boolean printJaasInfo = true;
//        if(printJaasInfo){
//            try{
//                System.err.println("LogoutServlet>userPrincipalName:"+(request.getUserPrincipal()==null?"null":request.getUserPrincipal().getName()));//ybxiang
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//        response.setHeader("Cache-Control", "no-cache, no-store");
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Expires", new java.util.Date().toString());//http://www.coderanch.com/t/541412/Servlets/java/Logout-servlet-button
//        if(request.getSession(false)!=null){
//            request.getSession(false).invalidate();
//        }
//        if(request.getSession()!=null){
//            request.getSession().invalidate();//remove session.
//        }
//        request.logout();//JAAS log out (from servlet specification)! It is a MUST!
//        if(printJaasInfo){
//            try{
//                System.err.println("LogoutServlet>userPrincipalName:"+(request.getUserPrincipal()==null?"null":request.getUserPrincipal().getName()));//ybxiang
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//}

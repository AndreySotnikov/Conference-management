package project;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by andrey on 14.07.15.
 */
@Path("/reporter")
@WebService
public class ReporterController {

    @GET
    @Path("test")
    public String reporterTest(){
        return  "I am reporter";
    }
}

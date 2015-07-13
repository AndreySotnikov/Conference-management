package project;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by andrey on 13.07.15.
 */
@Path("/")
public class Rest {
    @GET
    @Path("echo")
    public String echo(@QueryParam("q") String original) {
        return original;
    }
}
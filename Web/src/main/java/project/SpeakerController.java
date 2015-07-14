package project;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by andrey on 14.07.15.
 */
@Path("/speaker")
public class SpeakerController {

    @GET
    @Path("test")
    public String speakerTest(){
        return  "I am speaker";
    }
}

package project;

import project.Service.RoomService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by nikitayakuntsev on 17.07.15.
 */

@Path("/")
@Stateless
public class RoomController {
    @EJB
    private RoomService roomService;
}

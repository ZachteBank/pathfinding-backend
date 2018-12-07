package restserver.restservices;

import models.entities.Device;
import restserver.handlers.IDeviceHandler;
import restserver.handlers.IHandler;
import restserver.response.Reply;
import utils.GsonUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("pathfinding")
public class PathService {
    private static IDeviceHandler handler;
    private Class<Device> entityClass = Device.class;

    public static void setHandler(IDeviceHandler handler) {
        PathService.handler = handler;
    }

    @GET
    @Path("/all")
    public Response all() {
        Reply reply = handler.getAll();
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response save(String data) {
        //Device entity = GsonUtils.fromJson(data, entityClass);

        System.out.println(data);

        List<Device> devices = new ArrayList<>();
        Reply reply = handler.addDevices(devices);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}

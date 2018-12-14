package restserver.restservices;

import models.entities.Beacon;
import models.entities.Device;
import models.json.AddDevicesJson;
import models.json.DevicesJson;
import restserver.handlers.IBeaconHandler;
import restserver.handlers.IDeviceHandler;
import restserver.handlers.IHandler;
import restserver.response.Reply;
import utils.GsonUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("pathfinding")
public class PathService {
    private static IBeaconHandler handler;
    private Class<Beacon> entityClass = Beacon.class;


    public static void setHandler(IBeaconHandler handler) {
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
        AddDevicesJson json = GsonUtils.fromJson(data, AddDevicesJson.class);

        System.out.println(data);
        System.out.println(json.getBeacon());

        Reply reply = handler.addDevices(json);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}

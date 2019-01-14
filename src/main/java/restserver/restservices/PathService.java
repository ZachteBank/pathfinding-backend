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

    /**
     * Set the handler to process the request
     * @param handler An implementation of an handler
     */
    public static void setHandler(IBeaconHandler handler) {
        PathService.handler = handler;
    }

    /**
     * Get all beacons from the system
     * @return Status code with message
     */
    @GET
    @Path("/all")
    public Response all() {
        Reply reply = handler.getAll();
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    /**
     * Save function for the json
     * Add all the beacons to a list and parse the devices
     * @param data Json formatted data, see readme for format
     * @return Status code with message
     */
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

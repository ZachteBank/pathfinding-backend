package restserver;

import logging.Logger;
import models.repository.BeaconRepository;
import models.repository.DeviceRepository;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.servlet.ServletContainer;
import processing.core.PApplet;
import restserver.handlers.BeaconHandler;
import restserver.handlers.IBeaconHandler;
import restserver.handlers.IDeviceHandler;
import restserver.restservices.PathService;
import visualisation.Client;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class RestService {

    public static void main(String[] args) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        Server jettyServer = new Server(8070);

        //region Origin header
        FilterHolder cors = context.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
        //endregion

        IBeaconHandler beaconHandler = new BeaconHandler(new BeaconRepository());
        PathService.setHandler(beaconHandler);

        jettyServer.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.

        jerseyServlet.setInitParameter("jersey.config.server.provider.packages",
                "restserver.restservices");

        Client client = new Client(beaconHandler.getBeacons());

        String[] processingArgs = {"Visualisation"};
        PApplet.runSketch(processingArgs, client);

        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            Logger.getInstance().log(e);
        } finally {
            jettyServer.destroy();
        }
    }
}

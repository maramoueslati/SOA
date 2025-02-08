package webservice;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.awt.*;
@Path("/hello")
    public class HelloRessources {
        @GET
        @Path("/hi")
        @Produces(MediaType.TEXT_PLAIN)
        public Response sayHello() {
            return Response.status(200).entity("Hello World").build();
        }
        @GET
        @Path("/{name}")
        @Produces(MediaType.TEXT_PLAIN)
        public Response sayHelloTo(@PathParam(value="name")  String name) {
            return Response.status(200).entity("Hello "+name+"!").build();
        }
}

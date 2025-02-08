package webservice;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/logements")
public class LogementRessource {
    LogementBusiness helper=new LogementBusiness();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLogements() {
        return Response.ok(helper.getLogements()).build();
    }
    @GET
    @Path("/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementByReference(@PathParam("reference") int reference) {
        Logement logement = (Logement) helper.getLogementsByReference(reference);
        if (logement != null) {
            return Response.ok(logement).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        boolean added = helper.addLogement(logement);
        if (added) {
            return Response.status(Response.Status.CREATED).entity(logement).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }




    @PUT
    @Path("/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        boolean updated = helper.updateLogement(reference, logement);
        if (updated) {
            return Response.ok(logement).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{reference}")
    public Response deleteLogement(@PathParam("reference") int reference) {
        boolean deleted = helper.deleteLogement(reference);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}



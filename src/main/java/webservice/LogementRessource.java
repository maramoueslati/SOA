package webservice;

import entities.Logement;
import metiers.LogementBusiness;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/logements")
@Api(value = "Logement", description = "API pour la gestion des logements")
public class LogementRessource {
    LogementBusiness helper = new LogementBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Récupérer tous les logements",
            response = Logement.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des logements récupérée avec succès")
    })
    public Response getAllLogements() {
        return Response.ok(helper.getLogements()).build();
    }

    @GET
    @Path("/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Récupérer un logement par sa référence",
            response = Logement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Logement trouvé"),
            @ApiResponse(code = 404, message = "Logement non trouvé")
    })
    public Response getLogementByReference(
            @ApiParam(value = "Référence du logement", required = true)
            @PathParam("reference") int reference) {
        Logement logement = (Logement) helper.getLogementsByReference(reference);
        if (logement != null) {
            return Response.ok(logement).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Ajouter un nouveau logement",
            response = Logement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Logement créé avec succès"),
            @ApiResponse(code = 400, message = "Données de logement invalides")
    })
    public Response addLogement(
            @ApiParam(value = "Logement à ajouter", required = true)
            Logement logement) {
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
    @ApiOperation(value = "Mettre à jour un logement existant",
            response = Logement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Logement mis à jour avec succès"),
            @ApiResponse(code = 404, message = "Logement non trouvé")
    })
    public Response updateLogement(
            @ApiParam(value = "Référence du logement à mettre à jour", required = true)
            @PathParam("reference") int reference,
            @ApiParam(value = "Nouvelles données du logement", required = true)
            Logement logement) {
        boolean updated = helper.updateLogement(reference, logement);
        if (updated) {
            return Response.ok(logement).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{reference}")
    @ApiOperation(value = "Supprimer un logement")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Logement supprimé avec succès"),
            @ApiResponse(code = 404, message = "Logement non trouvé")
    })
    public Response deleteLogement(
            @ApiParam(value = "Référence du logement à supprimer", required = true)
            @PathParam("reference") int reference) {
        boolean deleted = helper.deleteLogement(reference);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
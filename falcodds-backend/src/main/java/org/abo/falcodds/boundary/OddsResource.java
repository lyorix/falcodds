package org.abo.falcodds.boundary;

import org.abo.falcodds.control.OddsControl;
import org.abo.falcodds.model.EmpireConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

@Path("/odds")
public class OddsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(OddsResource.class);

    @Inject
    OddsControl oddsControl;

    @POST
    public Response getOdds(EmpireConfiguration empirePlan) {
        if (empirePlan == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new GenericEntity<String>("empire plan parameter must be defined") {
            }).build();
        }
        if (empirePlan.getCountdown() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new GenericEntity<String>("count down must be defined") {
            }).build();
        }
        if (empirePlan.getBountyHunters() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new GenericEntity<String>("bounty hunters must be defined") {
            }).build();
        }
        LOGGER.debug("get odds for empire plan: " + empirePlan);

        Long odds = oddsControl.getOdds(empirePlan);

        return Response.status(Response.Status.OK).entity(new GenericEntity<Long>(odds) {
        }).build();
    }
}
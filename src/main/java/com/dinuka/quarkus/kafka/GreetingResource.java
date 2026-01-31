package com.dinuka.quarkus.kafka;

import org.slf4j.Logger;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/hello")
public class GreetingResource {

  Logger logger = org.slf4j.LoggerFactory.getLogger(GreetingResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        logger.info("Received request for /hello endpoint");
        return "Hello from Quarkus REST";
    }
}

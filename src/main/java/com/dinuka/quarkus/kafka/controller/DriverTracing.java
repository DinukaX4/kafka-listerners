package com.dinuka.quarkus.kafka.controller;

import org.slf4j.Logger;

import com.dinuka.quarkus.kafka.DriverTracingModel;
import com.dinuka.quarkus.kafka.event.producer.DriverTrackingEventPublisher;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/driver-tracing")
public class DriverTracing {
  Logger logger = org.slf4j.LoggerFactory.getLogger(DriverTracing.class);

  private final DriverTrackingEventPublisher driverTrackingEventPublisher;

  public DriverTracing(DriverTrackingEventPublisher driverTrackingEventPublisher) {
    this.driverTrackingEventPublisher = driverTrackingEventPublisher;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response handleDriverTracing(@RequestBody DriverTracingModel request) {
    logger.info("Received request for /v1/driver-tracing endpoint");
    driverTrackingEventPublisher.publish(request);
    return Response.ok("Driver tracing event received").build();
  }
}

package com.dinuka.quarkus.kafka.event.producer;

import java.util.UUID;

import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;

import com.dinuka.quarkus.kafka.event.DriverTracingEvent;
import com.dinuka.quarkus.kafka.DriverTracingModel;
import com.dinuka.quarkus.kafka.event.producer.mapper.DriverTracingEventMapper;

@ApplicationScoped
public class DriverTrackingEventPublisher {

  Logger log = org.slf4j.LoggerFactory.getLogger(DriverTrackingEventPublisher.class);

  private final Emitter<DriverTracingEvent> emitter;
  private final DriverTracingEventMapper mapper;

  public DriverTrackingEventPublisher(
      @Channel("driver-tracing-out") final Emitter<DriverTracingEvent> emitter,
      final DriverTracingEventMapper mapper) {
    this.emitter = emitter;
    this.mapper = mapper;
  }

  public void publish(final DriverTracingModel model) {
    final var event = mapper.toAvro(model);
    publishEvent(UUID.randomUUID().toString(), event);
  }

  private void publishEvent(final String messageKey, final DriverTracingEvent event) {
    log.info("Publishing event to Kafka (messageKey: {})", messageKey);
    final Message<DriverTracingEvent> message = KafkaRecord.of(messageKey, event);
    emitter.send(message);
  }
}

package com.dinuka.quarkus.kafka.event.producer.mapper;

import org.mapstruct.Mapper;

import com.dinuka.quarkus.kafka.DriverTracingModel;
import com.dinuka.quarkus.kafka.event.DriverTracingEvent;

@Mapper(componentModel = "jakarta")
public interface DriverTracingEventMapper {
  DriverTracingModel toModel(DriverTracingEvent avro);

  DriverTracingEvent toAvro(DriverTracingModel model);
}

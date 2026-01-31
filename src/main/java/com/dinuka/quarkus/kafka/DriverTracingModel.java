package com.dinuka.quarkus.kafka;

public record DriverTracingModel(
    String driverId, long eventTimestamp, double latitude, double longitude, String eventType) {}

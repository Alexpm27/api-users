package com.example.securitywithjwt.persistence.models.projections;

public interface StatisticsProjection {
    Long getId();

    Float getHumidity_above();

    Float getHumidity_below();

    Float getTemperature();

    Float getLux();

    String getStatus();

    Float getMedian();

    String getDate();

}

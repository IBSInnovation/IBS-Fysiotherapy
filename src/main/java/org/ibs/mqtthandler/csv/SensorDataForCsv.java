package org.ibs.mqtthandler.csv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SensorDataForCsv {
    @JsonProperty("data")
    private String data;

    @JsonIgnore
    private String id;

}

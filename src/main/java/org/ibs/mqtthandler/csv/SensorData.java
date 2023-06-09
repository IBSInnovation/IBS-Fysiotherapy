package org.ibs.mqtthandler.csv;

import java.io.File;

public class SensorData {
    public String id;

    public String data;

    public SensorData(String id, String data) {
        this.data = data;
        this.id = id;
    }
}

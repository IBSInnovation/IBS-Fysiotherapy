package org.ibs.mqtthandler.service;

import org.ibs.mqtthandler.csv.SensorData;
import org.ibs.mqtthandler.csv.csvHandler;
import org.ibs.mqtthandler.domain.Mqtt;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class MqttService {

    private Mqttsubscriber mqttsubscriber;
    private MqttPublisher mqttpublisher;
    private csvHandler csvHandler;
    //private MqttRepository mqttRepository;

    public MqttService(Mqttsubscriber mqttsubscriber, MqttPublisher mqttPublisher,csvHandler csvHandler) {
        this.mqttsubscriber = mqttsubscriber;
        this.mqttpublisher = mqttPublisher;
        this.csvHandler = csvHandler;
        //this.mqttRepository = mqttRepository;
    }

    public void startMeasurement(String topic, String content) {
        this.mqttpublisher.publishMessageMqtt(topic, content);
    }

    public void stopMeasurement(String topic, String content) {
        this.mqttpublisher.publishMessageMqtt(topic, content);
    }

    public void connectForSensorData(String topic) {
        this.mqttsubscriber.connectMQTT(topic);
    }

    private String getDataFromJsonFile(String id) {
        return this.mqttsubscriber.getData(id);
    }

    public Double getAverageFromData(String id) {
        List<List<String>> list = this.mqttsubscriber.dataToStringList(getDataFromJsonFile(id));
        return this.mqttsubscriber.convertToSingleDoubleListAndCalculateAverage(list);
    }


    public ResponseEntity<FileSystemResource> getCSVFile(String id){
        return csvHandler.getCSVFile2(id);
    }

    public void generateCsv(String id) throws IOException {
        csvHandler.generateCsv(id);
    }
}

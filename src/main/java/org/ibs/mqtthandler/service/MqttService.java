package org.ibs.mqtthandler.service;

import org.ibs.mqtthandler.csv.SensorData;
import org.ibs.mqtthandler.csv.csvHandler;
import org.ibs.mqtthandler.data.MqttRepository;
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

    public MqttService(Mqttsubscriber mqttsubscriber, MqttPublisher mqttPublisher) {
        this.mqttsubscriber = mqttsubscriber;
        this.mqttpublisher = mqttPublisher;
        this.csvHandler = csvHandler;
        //this.mqttRepository = mqttRepository;
    }

    public void statusMeasurement(String topic, String content) {
        this.mqttpublisher.publishMessageMqtt(topic, content);
    }

    public void connectForSensorData(String topic) {
        this.mqttsubscriber.connectMQTT(topic);
    }



}

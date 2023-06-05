package org.ibs.mqtthandler.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MqttService {

    private Mqttsubscriber mqttsubscriber;
    private MqttPublisher mqttpublisher;

    public MqttService(Mqttsubscriber mqttsubscriber, MqttPublisher mqttPublisher) {
        this.mqttsubscriber = mqttsubscriber;
        this.mqttpublisher = mqttPublisher;
    }

    public void statusMeasurement(String topic, String content) {
        this.mqttpublisher.publishMessageMqtt(topic, content);
    }

    public void connectForSensorData(String topic) {
        this.mqttsubscriber.connectMQTT(topic);
    }



}

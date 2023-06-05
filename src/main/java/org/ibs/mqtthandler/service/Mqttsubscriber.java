package org.ibs.mqtthandler.service;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Mqttsubscriber {
    //TODO function maken van hieronder
    //TODO Presentation class with API call
    //TODO gemiddelde pakken van al deze opgeslagen data
    public static void main(String[] args) {
        String broker = "tcp://nhagens02.duckdns.org:1883";
        String topic = "dot/sensordata";

        String username = "nhagens02_ext";
        String password = "makingapasswordsucks";
        String clientid = "subscribe_client_backend";

        int qos = 0;

        try {
            System.out.println("Try");
            MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
            // connect options
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            System.out.println("Configure options");
            // setup callback
            client.setCallback(new MqttCallback() {

                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost: " + cause.getMessage());
                }

                public void messageArrived(String topic, MqttMessage message) {
                    System.out.println("topic: " + topic);
                    System.out.println("Qos: " + message.getQos());
                    System.out.println("message content: " + message.getPayload());

                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------" + token.isComplete());
                }

            });
            client.connect(options);
            System.out.println("connected");
            client.subscribe(topic, qos);
            System.out.println("subscribed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

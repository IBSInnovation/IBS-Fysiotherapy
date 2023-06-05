package org.ibs.mqtthandler.service;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Mqttsubscriber {
    //TODO gemiddelde pakken van al deze opgeslagen dat
    String broker = "tcp://nhagens02.duckdns.org:1883";

    String username = "nhagens02_ext";
    String password = "makingapasswordsucks";
    String clientid = "subscribe_client_backend";

    int qos = 1;

    List<List<String>> dataListS1 = new ArrayList<>();
    List<List<String>> dataListS2 = new ArrayList<>();

    public List connectMQTT(String topic) {

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
                    System.out.println("message content: " + new String(message.getPayload()));

                    byteToStringList(message.getPayload());

                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------" + token.isComplete());
                }

            });
            client.connect(options);
            System.out.println("connected");
            client.subscribe(topic, qos);
            System.out.println("subscribed");
            return dataListS2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataListS2;
    }

    private List<String> byteToStringList(byte[] bytelist) {
        String byteToString = new String(bytelist);

        List<String> splitList = Arrays.asList(byteToString.split(","));

        List list1 = new ArrayList<>();

        List list2 = new ArrayList<>();

        for (String item : splitList) {
            if (splitList.indexOf(item) < 7) {
                list1.add(item);
            }
            else {
                dataListS1.add(list1);
            }
        }

        for (String item : splitList) {
            if (splitList.indexOf(item) > 6) {
                list2.add(item);
            }
            else {
                dataListS2.add(list2);
            }
        }
        System.out.println("s1 " + dataListS1);
        System.out.println("s2 " + dataListS2);
        return splitList;

    }
}


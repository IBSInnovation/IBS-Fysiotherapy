package org.ibs.mqtthandler.service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

@Component
public class MqttPublisher {

        String broker = "tcp://nhagens02.duckdns.org:1883";

        String username = "nhagens02_ext";
        String password = "makingapasswordsucks";
        String clientid = "publish_client_backend";

        int qos = 1;

        public void publishMessageMqtt(String topic, String content) {

            try {
                MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
                MqttConnectOptions options = new MqttConnectOptions();
                options.setUserName(username);
                options.setPassword(password.toCharArray());
                options.setConnectionTimeout(60);
                options.setKeepAliveInterval(60);
                // connect
                client.connect(options);
                // create message and setup QoS
                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(qos);
                // publish message
                client.publish(topic, message);
                System.out.println("Message published");
                System.out.println("topic: " + topic);
                System.out.println("message content: " + content);
                // disconnect
                client.disconnect();
                // close client
                client.close();
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
        }
}

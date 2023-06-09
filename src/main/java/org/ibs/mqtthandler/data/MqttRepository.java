package org.ibs.mqtthandler.data;

import org.ibs.mqtthandler.service.Mqttsubscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MqttRepository extends JpaRepository<Mqtt, Long> {

}

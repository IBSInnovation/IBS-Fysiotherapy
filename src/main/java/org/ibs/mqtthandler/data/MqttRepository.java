package org.ibs.mqtthandler.data;

import org.ibs.mqtthandler.domain.Mqtt;
import org.ibs.mqtthandler.service.Mqttsubscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MqttRepository extends JpaRepository<Mqtt, Long> {

}

package org.ibs.mqtthandler.presentation;

import org.ibs.mqtthandler.dto.InitialSensorDto;
import org.ibs.mqtthandler.dto.SubscribeSensorDTO;
import org.ibs.mqtthandler.service.MqttService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mqtt")
public class MqttController {

    private MqttService mqttService;

    public MqttController(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    @PostMapping("/start")
    public void startMeasurement(@RequestBody InitialSensorDto initialSensorDto){
        this.mqttService.statusMeasurement(initialSensorDto.topic, initialSensorDto.content);
    }

    @PostMapping("/stop")
    public void stopMeasurement(@RequestBody InitialSensorDto initialSensorDto) {
        this.mqttService.statusMeasurement(initialSensorDto.topic, initialSensorDto.content);
    }

    @GetMapping("/connect")
    public void connectSensor(@RequestBody SubscribeSensorDTO subscribeSensorDTO) {
        this.mqttService.connectForSensorData(subscribeSensorDTO.topic);
    }
}

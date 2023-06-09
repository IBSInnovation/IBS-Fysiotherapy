package org.ibs.mqtthandler.presentation;

import org.ibs.mqtthandler.dto.InitialSensorDto;
import org.ibs.mqtthandler.dto.SubscribeSensorDTO;
import org.ibs.mqtthandler.service.MqttService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/mqtt")
public class MqttController {

    private MqttService mqttService;

    public MqttController(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    @PostMapping("/start")
    public void startMeasurement(@RequestBody InitialSensorDto initialSensorDto){
        this.mqttService.startMeasurement(initialSensorDto.topic, initialSensorDto.content);
    }

    @PostMapping("/stop")
    public void stopMeasurement(@RequestBody InitialSensorDto initialSensorDto) {
        this.mqttService.stopMeasurement(initialSensorDto.topic, initialSensorDto.content);
        //this.mqttService.getAverageFromData();
    }

    @GetMapping("/connect")
    public void connectSensor(@RequestBody SubscribeSensorDTO subscribeSensorDTO) {
        this.mqttService.connectForSensorData(subscribeSensorDTO.topic);
    }

    @PostMapping("/csv/generate/{id}")
    public void generateCsv(@PathVariable String id) throws IOException {
         this.mqttService.generateCsv(id);
    }

    @GetMapping("/csv/get/{id}")
    public ResponseEntity<FileSystemResource> getCsv(@PathVariable String id){
        return this.mqttService.getCSVFile(id);
    }

    @GetMapping("/getAverage/{id}")
    public Double getAverageFromDataSet(@PathVariable String id) {
        return this.mqttService.getAverageFromData(id);
    }
}

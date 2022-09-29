package org.ibs.application.dto;

import lombok.Builder;
import org.ibs.utils.DTO;

import java.util.Date;

@Builder
public class MeasurementDTO extends DTO {
    public Long id;
    public Date dateOfMeasurement;
    public int data;
    public Long exerciseId;

}

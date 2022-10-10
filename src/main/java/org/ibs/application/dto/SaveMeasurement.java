package org.ibs.application.dto;

import lombok.Builder;
import org.ibs.utils.DTO;

import java.util.Date;

@Builder
public class SaveMeasurement extends DTO {
    public String id;
    public Date dateOfMeasurement;
    public int data;
    public String exerciseId;

}

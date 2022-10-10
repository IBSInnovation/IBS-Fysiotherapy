package org.ibs.application.dto.measurementdto;

import lombok.Builder;
import org.ibs.utils.DTO;

import java.util.Date;

@Builder
public class GetMeasurement extends DTO {
    public String id;
    public Date dateOfMeasurement;
    public int data;
}

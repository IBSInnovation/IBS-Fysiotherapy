package org.ibs.application.dto.measurementdto;

import lombok.NoArgsConstructor;
import org.ibs.utils.DTO;

@NoArgsConstructor
public class AskMeasurement extends DTO {
    public String id;
    public String patientId;
    public String categoryId;
    public String exerciseId;
}

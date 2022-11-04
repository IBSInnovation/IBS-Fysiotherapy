package org.ibs.application.dto.physiotherapistdto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SavePhysioPatient {
    public String physioId;
    public String patientId;
    public String email;
    public String name;
}

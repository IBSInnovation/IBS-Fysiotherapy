package org.ibs.application.dto.measurementdto;

import com.google.type.DateTime;
import org.ibs.utils.DTO;

import java.util.ArrayList;
import java.util.Date;

public class SaveMeasurement extends DTO {
    public String id;

    public String euler_x;

    public String euler_y;

    public String euler_z;

    public String acc_x;

    public String acc_y;

    public String acc_z;

    public String batteryNumber;

    public SaveMeasurement() {
    }
}

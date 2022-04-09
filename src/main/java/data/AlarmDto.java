package data;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class AlarmDto {

    private LocalTime time;
    private String event;
}

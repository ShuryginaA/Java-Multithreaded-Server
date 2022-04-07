package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ServerTimer {
    public Integer hour;
    public Integer minute;
    private LocalTime timer;

    public ServerTimer(){
        timer=LocalTime.of(0,0);
    }

    public String toString()
    {
        return "Current server time is "+timer.toString();


    }
}
package services;

import model.ServerTimer;

import java.time.LocalTime;
import java.util.Map;

import static services.Server.events;
import static services.Server.timer;

public class TimeChanger extends Thread{

    public TimeChanger(){
        setDaemon(true);
        start();
    }
    @Override
    public void run(){
        int currentH= timer.getTimer().getHour();
        int currentM= timer.getTimer().getMinute();
        while(true) {
            if(currentM==59) {
                timer.setTimer(LocalTime.of(currentH++,0));
            }
            else {
                timer.setTimer(LocalTime.of(currentH,currentM++));
            }
            try {
                for(Map.Entry<LocalTime,String> map:events.entrySet())
                {
                    if((timer.getTimer().equals(map.getKey())))
                        System.out.println("Alarm rings: "+map.getKey() +" "+ map.getValue());
                }
                sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

package services;

import model.ServerTimer;

public class TimeChanger extends Thread{

    public TimeChanger(){
        setDaemon(true);
        start();
    }
    @Override
    public void run(){
        int currentH= Server.timer.getHour();
        int currentM=Server.timer.getMinute();
        while(true) {
            if(currentM==59)
            Server.timer.setTime(currentH++, 0);
            else
                Server.timer.setMinute(currentM++);
            try {
                sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

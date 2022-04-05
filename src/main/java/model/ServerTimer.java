package model;

import lombok.Getter;

@Getter
public class ServerTimer {
    public int hour;
    public int minute;

    //Constructors
    public ServerTimer()
    {
        hour = 0;
        minute = 0;
    }

    public ServerTimer(int hour, int minute)
    {
        setHour(hour);
        setMinute(minute);
    }

    //Accessors
    public void setHour(int alarmH)
    {
        if((alarmH >= 0) && (alarmH <= 24))
            hour = alarmH;
        else
            System.out.println("Fatal error: invalid alarm hour");
    }

    public void setMinute(int alarmM)
    {
        if((alarmM >= 0) && (alarmM <= 59))
            minute = alarmM;
        else
            System.out.println("Fatal error: invalid alarm minute");
    }

    public void setTime(int h,int m)
    {
        if((m >= 0) && (m <= 59) && (h>=0)&& (h<=24)) {
            minute = m;
            hour = h;
        }
        else
            System.out.println("Fatal error: invalid alarm time");
    }


    public String getCurrentAlarmTime()
    {
        return "The alarm is set to " + hour + ":" + minute ;
    }

    //Facilitators
    public String toString()
    {
        return "Current server time is "+getHour() + ":" + getMinute();


    }

    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        else if(getClass() != o.getClass())
            return false;
        else
        {
            ServerTimer otherClock = (ServerTimer) o;
            return((getHour() == otherClock.getHour()) && (getMinute() == otherClock.getMinute())
                  ) && (hour == otherClock.hour)
                    && (minute == otherClock.minute);
        }

    }
}
package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class Message implements Serializable {
    private String time;
    private String event;

    @Override
    public String toString(){
        return time+"  "+event;
    }
}
